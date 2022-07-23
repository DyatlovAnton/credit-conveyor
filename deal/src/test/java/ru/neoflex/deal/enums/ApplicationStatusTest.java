package ru.neoflex.deal.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationStatusTest {
    ApplicationStatus status=ApplicationStatus.fromString("preapproval");
    @Test
    void testEnum(){
        System.out.println(status.getString());
        assertThrows(IllegalArgumentException.class,() -> {
            ApplicationStatus.fromString("test");
        });
    }

}