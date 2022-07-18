package ru.neoflex.conveyor.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "Элемент графика платежей")
public class PaymentScheduleElement {
    @Schema(description = "Номер")
    private final Integer number;
    @Schema(description = "Дата")
    private final LocalDate date;
    @Schema(description = "Сумма платежа")
    private final BigDecimal totalPayment;
    @Schema(description = "Плата по процентам")
    private final BigDecimal interestPayment;
    @Schema(description = "Плата по долгу")
    private final BigDecimal debtPayment;
    @Schema(description = "Оставшыйся долг")
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
