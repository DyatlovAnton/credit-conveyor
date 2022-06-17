package ru.neoflex.conveyor.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PaymentScheduleElementTest {

    BigDecimal bd = BigDecimal.ONE;
    PaymentScheduleElement element = new PaymentScheduleElement(1, LocalDate.now(), bd, bd, bd, bd);

    @Test
    void getNumber() {
        assertEquals(element.getNumber(), 1);
    }

    @Test
    void getDate() {
        assertEquals(element.getDate(), LocalDate.now());
    }

    @Test
    void getTotalPayment() {
        assertEquals(element.getTotalPayment(), bd);
    }

    @Test
    void getInterestPayment() {
        assertEquals(element.getInterestPayment(), bd);
    }

    @Test
    void getDebtPayment() {
        assertEquals(element.getDebtPayment(), bd);
    }

    @Test
    void getRemainingDebt() {
        assertEquals(element.getRemainingDebt(), bd);
    }
}