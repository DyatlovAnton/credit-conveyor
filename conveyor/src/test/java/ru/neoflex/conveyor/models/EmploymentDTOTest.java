package ru.neoflex.conveyor.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class EmploymentDTOTest {

    EmploymentDTO employmentDTO = new EmploymentDTO(EmploymentStatus.UNEMPLOYED, "789", BigDecimal.ONE, Position.WORKER, 30, 10);

    @Test
    void getEmployerINN() {
        assertEquals(employmentDTO.getEmployerINN(), "789");
    }
}