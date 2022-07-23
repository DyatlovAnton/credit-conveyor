package ru.neoflex.application.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class LoanOfferDTOTest {
    @Test
    void loanOfferDTOTest(){
        LoanOfferDTO loanOfferDTO0 = new LoanOfferDTO(1L, BigDecimal.valueOf(100000), BigDecimal.valueOf(132553.79), 60, BigDecimal.valueOf(2184.23), BigDecimal.valueOf(0.112), true, true);
        LoanOfferDTO loanOfferDTO1 = new LoanOfferDTO(1L, BigDecimal.valueOf(100000), BigDecimal.valueOf(132553.79), 60, BigDecimal.valueOf(2184.23), BigDecimal.valueOf(0.112), true, true);
        assertEquals(loanOfferDTO0, loanOfferDTO1);
        loanOfferDTO0.setApplicationId(loanOfferDTO0.getApplicationId());
        loanOfferDTO0.setTotalAmount(loanOfferDTO0.getTotalAmount());
        loanOfferDTO0.setRequestedAmount(loanOfferDTO0.getRequestedAmount());
        loanOfferDTO0.setTerm(loanOfferDTO0.getTerm());
        loanOfferDTO0.setMonthlyPayment(loanOfferDTO0.getMonthlyPayment());
        loanOfferDTO0.setRate(loanOfferDTO0.getRate());
        loanOfferDTO0.setIsInsuranceEnabled(loanOfferDTO0.getIsInsuranceEnabled());
        loanOfferDTO0.setIsSalaryClient(loanOfferDTO0.getIsSalaryClient());
    }
}