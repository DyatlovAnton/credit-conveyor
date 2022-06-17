package ru.neoflex.conveyor.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreditDTOTest {

    List<PaymentScheduleElement> list = Arrays.asList(
            new PaymentScheduleElement(1, LocalDate.now(), BigDecimal.valueOf(1), BigDecimal.valueOf(1), BigDecimal.valueOf(1), BigDecimal.valueOf(1)),
            new PaymentScheduleElement(1, LocalDate.now(), BigDecimal.valueOf(1), BigDecimal.valueOf(1), BigDecimal.valueOf(1), BigDecimal.valueOf(1))
    );
    CreditDTO creditDTO = new CreditDTO(BigDecimal.valueOf(100000), 6, BigDecimal.valueOf(1000), BigDecimal.valueOf(0.152), BigDecimal.valueOf(15.2), true, true, list);

    @Test
    void getAmount() {
        assertEquals(creditDTO.getAmount(), BigDecimal.valueOf(100000));
    }

    @Test
    void getTerm() {
        assertEquals(creditDTO.getTerm(), 6);
    }

    @Test
    void getMonthlyPayment() {
        assertEquals(creditDTO.getMonthlyPayment(), BigDecimal.valueOf(1000));
    }

    @Test
    void getRate() {
        assertEquals(creditDTO.getRate(), BigDecimal.valueOf(0.152));
    }

    @Test
    void getPsk() {
        assertEquals(creditDTO.getPsk(), BigDecimal.valueOf(15.2));
    }

    @Test
    void getIsInsuranceEnabled() {
        assertTrue(creditDTO.getIsInsuranceEnabled());
    }

    @Test
    void getIsSalaryClient() {
        assertTrue(creditDTO.getIsSalaryClient());
    }

    @Test
    void getPaymentSchedule() {
        assertEquals(creditDTO.getPaymentSchedule(), list);
    }
}