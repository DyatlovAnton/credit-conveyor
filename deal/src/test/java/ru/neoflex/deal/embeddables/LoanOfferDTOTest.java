package ru.neoflex.deal.embeddables;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class LoanOfferDTOTest {
    LoanOfferDTO loanOffer = new LoanOfferDTO(1L, BigDecimal.valueOf(100000), BigDecimal.valueOf(132553.79), 60, BigDecimal.valueOf(2184.23), BigDecimal.valueOf(0.112), true, true);
    LoanOfferDTO loanOffer1 = new LoanOfferDTO(1L, BigDecimal.valueOf(100000), BigDecimal.valueOf(132553.79), 60, BigDecimal.valueOf(2184.23), BigDecimal.valueOf(0.112), true, true);
    @Test
    void testEquals(){
        System.out.println(loanOffer.equals(loanOffer1));
    }

}