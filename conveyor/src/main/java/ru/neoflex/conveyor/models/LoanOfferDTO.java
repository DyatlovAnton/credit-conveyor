package ru.neoflex.conveyor.models;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class LoanOfferDTO {
    private final Long applicationId;
    private final BigDecimal requestedAmount;
    private final BigDecimal totalAmount;
    private final Integer term;
    private final BigDecimal monthlyPayment;
    private final BigDecimal rate;
    private final Boolean isInsuranceEnabled;
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
