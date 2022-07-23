package ru.neoflex.deal.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void fromString() {
        assertThrows(IllegalArgumentException.class,() -> {
            Position.fromString("test");
        });
    }
}