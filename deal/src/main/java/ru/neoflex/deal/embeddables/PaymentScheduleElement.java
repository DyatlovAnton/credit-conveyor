package ru.neoflex.deal.embeddables;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.math.BigDecimal;

@Getter
@Embeddable
public class PaymentScheduleElement {
    private Integer number;
    private LocalDate date;
    private BigDecimal totalPayment;
    private BigDecimal interestPayment;
    private BigDecimal debtPayment;
    private BigDecimal remainingDebt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentScheduleElement that = (PaymentScheduleElement) o;
        return number.equals(that.number) && date.equals(that.date) && totalPayment.equals(that.totalPayment) && interestPayment.equals(that.interestPayment) && debtPayment.equals(that.debtPayment) && remainingDebt.equals(that.remainingDebt);
    }
}
