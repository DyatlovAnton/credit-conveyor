package ru.neoflex.conveyor.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmploymentStatusTest {

    EmploymentStatus status = EmploymentStatus.EMPLOYED;

    @Test
    void testToString() {
        assertEquals("employed", status.toString());
    }

    @Test
    void fromString() {
        assertEquals(EmploymentStatus.fromString("employed"), status);
    }

    @Test
    void fromString_Should_Throw_Exception() {
        try {
            assertEquals(EmploymentStatus.fromString("test"), status);
        } catch (Exception exception) {
            assertEquals(exception.getClass(), IllegalArgumentException.class);
        }
    }
}