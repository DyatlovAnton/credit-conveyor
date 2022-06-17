package ru.neoflex.conveyor.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;

@Getter
public class PaymentScheduleElement {
    private final Integer number;
    private final LocalDate date;
    private final BigDecimal totalPayment;
    private final BigDecimal interestPayment;
    private final BigDecimal debtPayment;
    private final BigDecimal remainingDebt;

    public PaymentScheduleElement(Integer number, LocalDate date, BigDecimal totalPayment, BigDecimal interestPayment, BigDecimal debtPayment, BigDecimal remainingDebt) {
        this.number = number;
        this.date = date;
        this.totalPayment = totalPayment;
        this.interestPayment = interestPayment;
        this.debtPayment = debtPayment;
        this.remainingDebt = remainingDebt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentScheduleElement that = (PaymentScheduleElement) o;
        return number.equals(that.number) && date.equals(that.date) && totalPayment.equals(that.totalPayment) && interestPayment.equals(that.interestPayment) && debtPayment.equals(that.debtPayment) && remainingDebt.equals(that.remainingDebt);
    }
}
