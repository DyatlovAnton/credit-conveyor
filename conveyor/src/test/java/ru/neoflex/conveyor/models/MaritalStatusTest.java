package ru.neoflex.conveyor.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaritalStatusTest {

    MaritalStatus status = MaritalStatus.SINGLE;

    @Test
    void fromString() {
        assertEquals(MaritalStatus.fromString("single"), status);
    }

    @Test
    void fromString_Should_Throw_Exception() {
        try {
            assertEquals(MaritalStatus.fromString("test"), status);
        } catch (Exception exception) {
            assertEquals(exception.getClass(), IllegalArgumentException.class);
        }
    }
}