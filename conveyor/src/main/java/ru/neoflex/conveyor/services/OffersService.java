package ru.neoflex.conveyor.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.neoflex.conveyor.models.LoanApplicationRequestDTO;
import ru.neoflex.conveyor.models.LoanOfferDTO;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Slf4j
public class OffersService {
    @Value(value = "${rate}")
    private BigDecimal baseRate;

    @Value(value = "${insurance}")
    private BigDecimal insurance;

    private static final AtomicLong count = new AtomicLong(0);
    MathContext mc = MathContext.DECIMAL128;
    RoundingMode rm = RoundingMode.HALF_UP;

    public boolean prescore(LoanApplicationRequestDTO data) {
        String name = data.getFirstName();
        String surname = data.getLastName();
        String patronymic = data.getMiddleName();
        BigDecimal amount = data.getAmount();
        Integer term = data.getTerm();
        LocalDate birthdate = data.getBirthdate();
        String email = data.getEmail();
        String passportSeries = data.getPassportSeries();
        String passportNumber = data.getPassportNumber();
        int age = Period.between(birthdate, LocalDate.now()).getYears();
        return ((name.length() >= 2 && name.length() <= 30) && (surname.length() >= 2 && surname.length() <= 30) && ((patronymic.length() >= 2 && patronymic.length() <= 30) || (patronymic.length() == 0))) &&
                (amount.compareTo(BigDecimal.valueOf(10000)) >= 0) && (term >= 6) && birthdate.toString().matches("[\\d]{4}-[\\d]{2}-[\\d]{2}") && (age >= 18) &&
                (email.matches("[\\w\\.]{2,50}@[\\w\\.]{2,20}")) && (passportSeries.matches("\\d{4}") && passportNumber.matches("\\d{6}"));
    }

    public List<LoanOfferDTO> getLoanOfferList(LoanApplicationRequestDTO data) {

        List<LoanOfferDTO> list = new ArrayList<>();
        Double[] rateSubtract = {0.04, 0.03, 0.01, 0.0};
        boolean[][] insAndClient = {{true, true}, {true, false}, {false, true}, {false, false}};

        for (int i = 0; i < 4; i++) {
            BigDecimal rate = baseRate.subtract(BigDecimal.valueOf(rateSubtract[i]));
            BigDecimal monthRate = rate.divide(BigDecimal.valueOf(12), mc);
            long id = count.getAndIncrement();
            BigDecimal requestedAmount = data.getAmount();
            Integer term = data.getTerm();
            BigDecimal monthRatePlusOne = monthRate.add(BigDecimal.valueOf(1));
            BigDecimal monthRatePlusOnePowTerm = monthRatePlusOne.pow(term);
            BigDecimal annuitetTopPart = monthRate.multiply(monthRatePlusOnePowTerm);
            BigDecimal annuitetBottomPart = monthRatePlusOnePowTerm.subtract(BigDecimal.valueOf(1));
            BigDecimal annuitetK = annuitetTopPart.divide(annuitetBottomPart, mc);
            BigDecimal monthlyPayment = requestedAmount.multiply(annuitetK);
            BigDecimal totalAmount = monthlyPayment.multiply(BigDecimal.valueOf(term));
            if (i < 2) {
                totalAmount = totalAmount.add(requestedAmount.multiply(insurance));
            }
            LoanOfferDTO loanOfferDTO = new LoanOfferDTO(id, requestedAmount, totalAmount.setScale(2, rm), term, monthlyPayment.setScale(2, rm), rate, insAndClient[i][0], insAndClient[i][1]);
            log.info("API /conveyor/offers: " + i + "е предложение: " + loanOfferDTO);
            list.add(loanOfferDTO);
        }
        return list;
    }
}
