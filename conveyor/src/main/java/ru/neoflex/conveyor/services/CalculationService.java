package ru.neoflex.conveyor.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.neoflex.conveyor.models.CreditDTO;
import ru.neoflex.conveyor.models.PaymentScheduleElement;
import ru.neoflex.conveyor.models.ScoringDataDTO;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CalculationService {

    @Value(value = "${rate}")
    private BigDecimal baseRate;

    @Value(value = "${insurance}")
    private BigDecimal insurance;

    MathContext mc = MathContext.DECIMAL128;
    RoundingMode rm = RoundingMode.HALF_UP;

    public BigDecimal score(ScoringDataDTO data) {
        BigDecimal rate = baseRate;
        switch (data.getEmployment().getEmploymentStatus()) {
            case UNEMPLOYED:
                log.info("API /conveyor/calculation: Безработный. Скоринг не пройден");
                return null;
            case SELF_EMPLOYED:
                rate = rate.add(BigDecimal.valueOf(0.001));
                log.info("API /conveyor/calculation: Самоанятый. Ставка увеличина на 0.1%");
                break;
            case BUSINESS_OWNER:
                rate = rate.add(BigDecimal.valueOf(0.003));
                log.info("API /conveyor/calculation: Владелец бизнесса. Ставка увеличина на 0.3%");
                break;
        }
        switch (data.getEmployment().getPosition()) {
            case MID_MANAGER:
                rate = rate.subtract(BigDecimal.valueOf(0.002));
                log.info("API /conveyor/calculation: Менеджер среднего звена. Ставка уменьшина 0.2%");
                break;
            case TOP_MANAGER:
                rate = rate.subtract(BigDecimal.valueOf(0.004));
                log.info("API /conveyor/calculation: Топ менеджер. Ставка уменьшина на 0.4%");
                break;
        }
        if (data.getAmount().compareTo(data.getEmployment().getSalary().multiply(BigDecimal.valueOf(20))) > 0) {
            log.info("API /conveyor/calculation: Запрашиваемая сумма больше 20 зарплат. Скоринг не пройден");
            return null;
        }
        switch (data.getMaritalStatus()) {
            case MARRIED:
                rate = rate.subtract(BigDecimal.valueOf(0.003));
                log.info("API /conveyor/calculation: женат/замужем. Ставка уменьшина на 0.3%");
                break;
            case DIVORCED:
                rate = rate.add(BigDecimal.valueOf(0.001));
                log.info("API /conveyor/calculation: в разводе. Ставка увеличина 0.1%");
                break;
        }
        if (data.getDependentAmount() > 1) {
            rate = rate.add(BigDecimal.valueOf(0.001));
            log.info("API /conveyor/calculation: Число иждевенцев больше 1. Ставка увеличина на 0.1%");
        }
        int age = Period.between(data.getBirthdate(), LocalDate.now()).getYears();
        if (age < 20 || age > 60) {
            log.info("API /conveyor/calculation: Возраст меньше 20 или больше 60. Скоринг не пройден");
            return null;
        }
        switch (data.getGender()) {
            case FEMALE:
                if (age >= 35 && age <= 60) {
                    rate = rate.subtract(BigDecimal.valueOf(0.003));
                    log.info("API /conveyor/calculation: Женщина от 35 до 60 лет. Ставка умеьшина на 0.3%");
                }
                break;
            case MALE:
                if (age >= 30 && age <= 55) {
                    rate = rate.subtract(BigDecimal.valueOf(0.003));
                    log.info("API /conveyor/calculation: Мужчина от 30 до 55 лет. Ставка уменьшина на 0.3%");
                }
                break;
            case NON_BINARY:
                rate = rate.add(BigDecimal.valueOf(0.003));
                log.info("API /conveyor/calculation: Гендер не бинарный. Ставка увеличина на 0.3%");
                break;
        }
        if (data.getEmployment().getWorkExperienceTotal() < 12 && data.getEmployment().getWorkExperienceCurrent() < 3) {
            log.info("API /conveyor/calculation: общий рабочий стаж меньше 12 или текущий рабочий стаж меньше 3. Скоринг не пройден");
            return null;
        }
        if (data.getIsInsuranceEnabled()) {
            rate = rate.subtract(BigDecimal.valueOf(0.03));
            log.info("API /conveyor/calculation: Страхование. Ставка уменьшина на 3%");
        }
        if (data.getIsSalaryClient()) {
            rate = rate.subtract(BigDecimal.valueOf(0.01));
            log.info("API /conveyor/calculation: Зарплатный клиент. Ставка уменьшина на 1%");
        }
        return rate;
    }

    public CreditDTO scoreCreditDTO(ScoringDataDTO data, BigDecimal rate) {
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
