package ru.neoflex.conveyor.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    Position position = Position.OWNER;

    @Test
    void testToString() {
        assertEquals(position.toString(), "owner");
    }

    @Test
    void fromString() {
        assertEquals(Position.fromString("owner"), position);
    }

    @Test
    void fromString_Should_Throw_Exception() {
        try {
            assertEquals(Position.fromString("test"), position);
        } catch (Exception exception) {
            assertEquals(exception.getClass(), IllegalArgumentException.class);
        }
    }
}