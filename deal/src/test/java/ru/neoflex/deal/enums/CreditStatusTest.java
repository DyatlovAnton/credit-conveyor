package ru.neoflex.deal.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditStatusTest {
    CreditStatus status = CreditStatus.fromString("calculated");
    @Test
    void testCreditStatus(){
        System.out.println(status.getString());
        System.out.println(status.toString());
        assertThrows(IllegalArgumentException.class,() -> {
           CreditStatus.fromString("test");
        });
    }
}