package ru.neoflex.deal.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaritalStatusTest {

    @Test
    void fromString() {
        assertThrows(IllegalArgumentException.class,() -> {
            MaritalStatus.fromString("test");
        });
    }
}