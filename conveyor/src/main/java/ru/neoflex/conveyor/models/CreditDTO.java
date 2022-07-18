package ru.neoflex.conveyor.models;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "Сущность кредита")
public class CreditDTO {
    @Schema(description = "Сумма кредита")
    private final BigDecimal amount;
    @Schema(description = "Срок кредита")
    private final Integer term;
    @Schema(description = "Ежемесячный платеж")
    private final BigDecimal monthlyPayment;
    @Schema(description = "Процентная ставка")
    private final BigDecimal rate;
    @Schema(description = "Полная стоимость кредита")
    private final BigDecimal psk;
    @Schema(description = "Включена ли страховка")
    private final Boolean isInsuranceEnabled;
    @Schema(description = "Включен ли зарплатный клиент")
    private final Boolean isSalaryClient;
    @Schema(description = "График платежей")
    private final List<PaymentScheduleElement> paymentSchedule;

    public CreditDTO(BigDecimal amount, Integer term, BigDecimal monthlyPayment, BigDecimal rate, BigDecimal psk, Boolean isInsuranceEnabled, Boolean isSalaryClient, List<PaymentScheduleElement> paymentSchedule) {
        this.amount = amount;
        this.term = term;
        this.monthlyPayment = monthlyPayment;
        this.rate = rate;
        this.psk = psk;
        this.isInsuranceEnabled = isInsuranceEnabled;
        this.isSalaryClient = isSalaryClient;
        this.paymentSchedule = paymentSchedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditDTO creditDTO = (CreditDTO) o;
        return amount.equals(creditDTO.amount) && term.equals(creditDTO.term) && monthlyPayment.equals(creditDTO.monthlyPayment) && rate.equals(creditDTO.rate) && psk.equals(creditDTO.psk) && isInsuranceEnabled.equals(creditDTO.isInsuranceEnabled) && isSalaryClient.equals(creditDTO.isSalaryClient) && paymentSchedule.equals(creditDTO.paymentSchedule);
    }
}
