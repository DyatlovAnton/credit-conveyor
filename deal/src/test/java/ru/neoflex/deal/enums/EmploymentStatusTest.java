package ru.neoflex.deal.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmploymentStatusTest {

    @Test
    void fromString() {
        assertThrows(IllegalArgumentException.class,() -> {
            EmploymentStatus.fromString("test");
        });
    }
}