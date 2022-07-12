package ru.neoflex.deal.embeddables;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Getter
@Setter
@Embeddable
public class LoanOfferDTO {
    private Long applicationId;
    private BigDecimal requestedAmount;
    private BigDecimal totalAmount;
    private Integer term;
    private BigDecimal monthlyPayment;
    private BigDecimal rate;
    private Boolean isInsuranceEnabled;
    private Boolean isSalaryClient;


    @Override
    public String toString(){
        return "Application ID: "+applicationId+", Requested amount: "+requestedAmount+", Total amount: "+totalAmount+", Term: "+term+", Monthly payment: "+monthlyPayment+
                ", Rate: "+rate+", Is insurance Enabled: "+isInsuranceEnabled+", Is salary client: "+isSalaryClient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanOfferDTO that = (LoanOfferDTO) o;
        return applicationId.equals(that.applicationId) && requestedAmount.equals(that.requestedAmount) && totalAmount.equals(that.totalAmount) && term.equals(that.term) && monthlyPayment.equals(that.monthlyPayment) && rate.equals(that.rate) && isInsuranceEnabled.equals(that.isInsuranceEnabled) && isSalaryClient.equals(that.isSalaryClient);
    }
}
