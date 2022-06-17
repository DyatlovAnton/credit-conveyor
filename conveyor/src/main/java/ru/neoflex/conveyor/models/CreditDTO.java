package ru.neoflex.conveyor.models;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;

@Getter
public class CreditDTO {
    private final BigDecimal amount;
    private final Integer term;
    private final BigDecimal monthlyPayment;
    private final BigDecimal rate;
    private final BigDecimal psk;
    private final Boolean isInsuranceEnabled;
    private final Boolean isSalaryClient;
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
