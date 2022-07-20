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

    public PaymentScheduleElement() {
    }

    public PaymentScheduleElement(Integer number, LocalDate date, BigDecimal totalPayment, BigDecimal interestPayment, BigDecimal debtPayment, BigDecimal remainingDebt) {
        this.number = number;
        this.date = date;
        this.totalPayment = totalPayment;
        this.interestPayment = interestPayment;
        this.debtPayment = debtPayment;
        this.remainingDebt = remainingDebt;
    }

    @Override
    public String toString() {
        return "Number: " + number + ", Date: " + date + ", Total payment: " + totalPayment + ", Interest payment: " + interestPayment + ", Debt payment: " +
                debtPayment + ", Remaining debt: " + remainingDebt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentScheduleElement that = (PaymentScheduleElement) o;
        return number.equals(that.number) && date.equals(that.date) && totalPayment.equals(that.totalPayment) && interestPayment.equals(that.interestPayment) && debtPayment.equals(that.debtPayment) && remainingDebt.equals(that.remainingDebt);
    }
}
