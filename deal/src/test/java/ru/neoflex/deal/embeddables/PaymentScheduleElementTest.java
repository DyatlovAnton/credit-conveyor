package ru.neoflex.deal.embeddables;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PaymentScheduleElementTest {
    PaymentScheduleElement paymentScheduleElement = new PaymentScheduleElement();
    PaymentScheduleElement element = new PaymentScheduleElement(1, LocalDate.now(), BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE);

    @Test
    void testToString() {
        System.out.println(element);
    }

    @Test
    void testEquals() {
        PaymentScheduleElement element1 = new PaymentScheduleElement(1, LocalDate.now(), BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE);
        System.out.println(element.equals(element1));
    }

    @Test
    void getNumber() {
        System.out.println(element.getNumber());
    }

    @Test
    void getDate() {
        System.out.println(element.getDate());
    }

    @Test
    void getTotalPayment() {
        System.out.println(element.getTotalPayment());
    }

    @Test
    void getInterestPayment() {
        System.out.println(element.getInterestPayment());
    }

    @Test
    void getDebtPayment() {
        System.out.println(element.getDebtPayment());
    }

    @Test
    void getRemainingDebt() {
        System.out.println(element.getRemainingDebt());
    }
}