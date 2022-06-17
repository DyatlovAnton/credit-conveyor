package ru.neoflex.conveyor.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenderTest {

    Gender gender = Gender.MALE;

    @Test
    void fromString() {
        assertEquals(Gender.fromString("male"), gender);
    }

    @Test
    void fromString_Should_Throw_Exception() {
        try {
            assertEquals(Gender.fromString("test"), gender);
        } catch (Exception exception) {
            assertEquals(exception.getClass(), IllegalArgumentException.class);
        }
    }
}