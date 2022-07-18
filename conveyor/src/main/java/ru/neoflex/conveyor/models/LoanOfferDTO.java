package ru.neoflex.conveyor.models;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "Сущность кредитного предложения")
public class LoanOfferDTO {
    @Schema(description = "ID заявки")
    private final Long applicationId;
    @Schema(description = "Запрашиваямая сумма")
    private final BigDecimal requestedAmount;
    @Schema(description = "Общая сумма")
    private final BigDecimal totalAmount;
    @Schema(description = "Срок кредита")
    private final Integer term;
    @Schema(description = "Ежемесячный платеж")
    private final BigDecimal monthlyPayment;
    @Schema(description = "Процентная ставка")
    private final BigDecimal rate;
    @Schema(description = "Включена ли стахховка")
    private final Boolean isInsuranceEnabled;
    @Schema(description = "Включен ли зарплатный клиент")
    private final Boolean isSalaryClient;

    public LoanOfferDTO(Long applicationId, BigDecimal requestedAmount, BigDecimal totalAmount, Integer term, BigDecimal monthlyPayment, BigDecimal rate, Boolean isInsuranceEnabled, Boolean isSalaryClient) {
        this.applicationId = applicationId;
        this.requestedAmount = requestedAmount;
        this.totalAmount = totalAmount;
        this.term = term;
        this.monthlyPayment = monthlyPayment;
        this.rate = rate;
        this.isInsuranceEnabled = isInsuranceEnabled;
        this.isSalaryClient = isSalaryClient;
    }

    @Override
    public String toString() {
        return "Application ID: " + applicationId + ", Requested amount: " + requestedAmount + ", Total amount: " + totalAmount + ", Term: " + term + ", Monthly payment: " + monthlyPayment +
                ", Rate: " + rate + ", Is insurance Enabled: " + isInsuranceEnabled + ", Is salary client: " + isSalaryClient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanOfferDTO that = (LoanOfferDTO) o;
        return applicationId.equals(that.applicationId) && requestedAmount.equals(that.requestedAmount) && totalAmount.equals(that.totalAmount) && term.equals(that.term) && monthlyPayment.equals(that.monthlyPayment) && rate.equals(that.rate) && isInsuranceEnabled.equals(that.isInsuranceEnabled) && isSalaryClient.equals(that.isSalaryClient);
    }
}
