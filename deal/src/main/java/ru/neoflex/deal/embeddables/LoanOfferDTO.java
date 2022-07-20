package ru.neoflex.deal.embeddables;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Getter
@Setter
@Embeddable
@Schema(description = "Сущность предложения кредит")
public class LoanOfferDTO {
    @Schema(description = "ID заявки")
    private Long applicationId;
    @Schema(description = "Запрашиваемая сумма")
    private BigDecimal requestedAmount;
    @Schema(description = "Общая сумма")
    private BigDecimal totalAmount;
    @Schema(description = "Срок")
    private Integer term;
    @Schema(description = "Ежемесячный платеж")
    private BigDecimal monthlyPayment;
    @Schema(description = "Процентная ставка")
    private BigDecimal rate;
    @Schema(description = "Включена ли страховка")
    private Boolean isInsuranceEnabled;
    @Schema(description = "Включен ли зарплатный клиент")
    private Boolean isSalaryClient;


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
