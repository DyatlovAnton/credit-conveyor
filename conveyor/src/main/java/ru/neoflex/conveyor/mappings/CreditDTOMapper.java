package ru.neoflex.conveyor.mappings;

import lombok.extern.slf4j.Slf4j;
import ru.neoflex.conveyor.models.CreditDTO;
import ru.neoflex.conveyor.models.PaymentScheduleElement;
import ru.neoflex.conveyor.models.ScoringDataDTO;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CreditDTOMapper {
    MathContext mc = MathContext.DECIMAL128;
    RoundingMode rm = RoundingMode.HALF_UP;

    public CreditDTOMapper(){}

    public CreditDTO scoringDataDTOtoCreditDTOMapping(ScoringDataDTO data, BigDecimal rate) {
        BigDecimal amount = data.getAmount();
        Integer term = data.getTerm();
        log.info("API /conveyor/calculation: Срок в месяцах " + term);
        BigDecimal monthRate = rate.divide(BigDecimal.valueOf(12), mc);
        log.info("API /conveyor/calculation: месячная ставка " + monthRate);
        BigDecimal monthRatePlusOne = monthRate.add(BigDecimal.valueOf(1));
        BigDecimal monthRatePlusOnePowTerm = monthRatePlusOne.pow(term);
        BigDecimal annuitetTopPart = monthRate.multiply(monthRatePlusOnePowTerm);
        BigDecimal annuitetBottomPart = monthRatePlusOnePowTerm.subtract(BigDecimal.valueOf(1));
        BigDecimal annuitetK = annuitetTopPart.divide(annuitetBottomPart, mc);
        log.info("API /conveyor/calculation: Коэффициент аннуитета " + annuitetK);
        BigDecimal monthlyPayment = amount.multiply(annuitetK);
        log.info("API /conveyor/calculation: Месячный платёж " + monthlyPayment);
        BigDecimal totalAmount = monthlyPayment.multiply(BigDecimal.valueOf(term));
        log.info("API /conveyor/calculation: Всего будет выплачено " + totalAmount);
        Boolean isInsuranceEnabled = data.getIsInsuranceEnabled();
        log.info("API /conveyor/calculation: Страхование " + isInsuranceEnabled);
        Boolean isSalaryClient = data.getIsSalaryClient();
        log.info("API /conveyor/calculation: Зарплатный клиент " + isSalaryClient);

        BigDecimal i = getI(data.getTerm(), amount, monthlyPayment, 20);
        log.info("API /conveyor/calculation: Процентная ставка базового периода " + i.toString());

        BigDecimal psk = i.multiply(BigDecimal.valueOf(100 * 12));
        log.info("API /conveyor/calculation: ПСК " + psk);

        List<PaymentScheduleElement> paymentSchedule = new ArrayList<>();
        BigDecimal remainingDebt = amount;
        LocalDate today = LocalDate.now();
        for (int x = 1; x <= term; x++) {
            BigDecimal interestPayment = remainingDebt.multiply(rate).divide(BigDecimal.valueOf(12), mc);
            BigDecimal debtPayment = monthlyPayment.subtract(interestPayment);
            remainingDebt = remainingDebt.subtract(debtPayment);
            LocalDate date = today.plusMonths(x);
            log.info("API /conveyor/calculation: " + x + "-й платёж: Дата " + date + ", Месячный платеж " + monthlyPayment + ", Выплата процентов " + interestPayment + ", Погашение долга " + debtPayment + ", Оставшийся долг " + remainingDebt);
            paymentSchedule.add(new PaymentScheduleElement(x, date, monthlyPayment.setScale(2, rm), interestPayment.setScale(2, rm), debtPayment.setScale(2, rm), remainingDebt.setScale(2, rm)));
        }
        return new CreditDTO(totalAmount.setScale(2, rm), term, monthlyPayment.setScale(2, rm), rate, psk.setScale(3, rm), isInsuranceEnabled, isSalaryClient, paymentSchedule);
    }

    private BigDecimal getI(Integer term, BigDecimal amount, BigDecimal monthlyPayment, int iAccuracy) {
        BigDecimal i = BigDecimal.valueOf(0);
        for (int j = 0; j <= iAccuracy; j++) {
            i = getITest(i, term, amount, monthlyPayment, j);
        }
        return i;
    }

    private BigDecimal getITest(BigDecimal i, Integer term, BigDecimal amount, BigDecimal monthlyPayment, int accuracy) {
        String acc = "0." + "0".repeat(accuracy) + "1";
        boolean equality = false;
        while (!equality) {
            BigDecimal leftSide = BigDecimal.valueOf(0);
            for (int j = 1; j <= term; j++) {
                BigDecimal onePlusI = i.add(BigDecimal.valueOf(1));
                BigDecimal onePlusIPowQk = onePlusI.pow(j);
                leftSide = leftSide.add(monthlyPayment.divide(onePlusIPowQk, mc));
            }
            BigDecimal difference = leftSide.subtract(amount);
            if (difference.compareTo(BigDecimal.valueOf(0)) <= 0) {
                i = i.subtract(new BigDecimal(acc));
                equality = true;
            } else {
                i = i.add(new BigDecimal(acc));
            }
        }
        return i;
    }
}
