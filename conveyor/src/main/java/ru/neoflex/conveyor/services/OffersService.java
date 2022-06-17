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
        long id = count.getAndIncrement();
        BigDecimal requestedAmount = data.getAmount();
        Integer term = data.getTerm();
        BigDecimal rate = baseRate.subtract(BigDecimal.valueOf(0.04));
        BigDecimal monthRate = rate.divide(BigDecimal.valueOf(12), mc);
        BigDecimal monthRatePlusOne = monthRate.add(BigDecimal.valueOf(1));
        BigDecimal monthRatePlusOnePowTerm = monthRatePlusOne.pow(term);
        BigDecimal annuitetTopPart = monthRate.multiply(monthRatePlusOnePowTerm);
        BigDecimal annuitetBottomPart = monthRatePlusOnePowTerm.subtract(BigDecimal.valueOf(1));
        BigDecimal annuitetK = annuitetTopPart.divide(annuitetBottomPart, mc);
        BigDecimal monthlyPayment = requestedAmount.multiply(annuitetK);
        BigDecimal totalAmount = monthlyPayment.multiply(BigDecimal.valueOf(term)).add(requestedAmount.multiply(insurance));
        LoanOfferDTO loanOfferDTO = new LoanOfferDTO(id, requestedAmount, totalAmount.setScale(2, rm), term, monthlyPayment.setScale(2, rm), rate, true, true);
        log.info("API /conveyor/offers: Первое предложение: " + loanOfferDTO);
        list.add(loanOfferDTO);

        id = count.getAndIncrement();
        rate = baseRate.subtract(BigDecimal.valueOf(0.03));
        monthRate = rate.divide(BigDecimal.valueOf(12), mc);
        monthRatePlusOne = monthRate.add(BigDecimal.valueOf(1));
        monthRatePlusOnePowTerm = monthRatePlusOne.pow(term);
        annuitetTopPart = monthRate.multiply(monthRatePlusOnePowTerm);
        annuitetBottomPart = monthRatePlusOnePowTerm.subtract(BigDecimal.valueOf(1));
        annuitetK = annuitetTopPart.divide(annuitetBottomPart, mc);
        monthlyPayment = requestedAmount.multiply(annuitetK);
        totalAmount = monthlyPayment.multiply(BigDecimal.valueOf(term)).add(requestedAmount.multiply(insurance));
        loanOfferDTO = new LoanOfferDTO(id, requestedAmount, totalAmount.setScale(2, rm), term, monthlyPayment.setScale(2, rm), rate, true, false);
        log.info("API /conveyor/offers: Второе предложение: " + loanOfferDTO);
        list.add(loanOfferDTO);

        id = count.getAndIncrement();
        rate = baseRate.subtract(BigDecimal.valueOf(0.01));
        monthRate = rate.divide(BigDecimal.valueOf(12), mc);
        monthRatePlusOne = monthRate.add(BigDecimal.valueOf(1));
        monthRatePlusOnePowTerm = monthRatePlusOne.pow(term);
        annuitetTopPart = monthRate.multiply(monthRatePlusOnePowTerm);
        annuitetBottomPart = monthRatePlusOnePowTerm.subtract(BigDecimal.valueOf(1));
        annuitetK = annuitetTopPart.divide(annuitetBottomPart, mc);
        monthlyPayment = requestedAmount.multiply(annuitetK);
        totalAmount = monthlyPayment.multiply(BigDecimal.valueOf(term));
        loanOfferDTO = new LoanOfferDTO(id, requestedAmount, totalAmount.setScale(2, rm), term, monthlyPayment.setScale(2, rm), rate, false, true);
        log.info("API /conveyor/offers: Третье предложение: " + loanOfferDTO);
        list.add(loanOfferDTO);

        id = count.getAndIncrement();
        rate = baseRate;
        monthRate = rate.divide(BigDecimal.valueOf(12), mc);
        monthRatePlusOne = monthRate.add(BigDecimal.valueOf(1));
        monthRatePlusOnePowTerm = monthRatePlusOne.pow(term);
        annuitetTopPart = monthRate.multiply(monthRatePlusOnePowTerm);
        annuitetBottomPart = monthRatePlusOnePowTerm.subtract(BigDecimal.valueOf(1));
        annuitetK = annuitetTopPart.divide(annuitetBottomPart, mc);
        monthlyPayment = requestedAmount.multiply(annuitetK);
        totalAmount = monthlyPayment.multiply(BigDecimal.valueOf(term));
        loanOfferDTO = new LoanOfferDTO(id, requestedAmount, totalAmount.setScale(2, rm), term, monthlyPayment.setScale(2, rm), rate, false, false);
        log.info("API /conveyor/offers: Четвёртое предложение: " + loanOfferDTO);
        list.add(loanOfferDTO);
        return list;
    }
}
