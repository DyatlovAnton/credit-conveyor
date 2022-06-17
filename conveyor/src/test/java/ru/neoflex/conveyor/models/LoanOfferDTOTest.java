package ru.neoflex.conveyor.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class LoanOfferDTOTest {

    BigDecimal bd = BigDecimal.ONE;
    LoanOfferDTO loanOfferDTO = new LoanOfferDTO(0L, bd, bd, 6, bd, bd, true, true);

    @Test
    void getApplicationId() {
        assertEquals(0L, loanOfferDTO.getApplicationId());
    }

    @Test
    void getRequestedAmount() {
        assertEquals(bd, loanOfferDTO.getRequestedAmount());
    }

    @Test
    void getTotalAmount() {
        assertEquals(bd, loanOfferDTO.getTotalAmount());
    }

    @Test
    void getTerm() {
        assertEquals(loanOfferDTO.getTerm(), 6);
    }

    @Test
    void getMonthlyPayment() {
        assertEquals(bd, loanOfferDTO.getMonthlyPayment());
    }

    @Test
    void getRate() {
        assertEquals(bd, loanOfferDTO.getRate());
    }

    @Test
    void getIsInsuranceEnabled() {
        assertTrue(loanOfferDTO.getIsInsuranceEnabled());
    }

    @Test
    void getIsSalaryClient() {
        assertTrue(loanOfferDTO.getIsSalaryClient());
    }
}