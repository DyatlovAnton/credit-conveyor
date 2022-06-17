package ru.neoflex.conveyor.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ScoringDataDTOTest {

    ScoringDataDTO data = new ScoringDataDTO(BigDecimal.ONE, 6, "a", "a", "a", Gender.MALE,
            LocalDate.now(), "1", "1", LocalDate.now(), "a", MaritalStatus.SINGLE,
            0, new EmploymentDTO(
            EmploymentStatus.EMPLOYED, "1", BigDecimal.ONE, Position.WORKER, 1, 1
    ), "a", true, true);

    @Test
    void getFirstName() {
        assertEquals(data.getFirstName(), "a");
    }

    @Test
    void getLastName() {
        assertEquals(data.getLastName(), "a");
    }

    @Test
    void getMiddleName() {
        assertEquals(data.getMiddleName(), "a");
    }

    @Test
    void getPassportSeries() {
        assertEquals(data.getPassportSeries(), "1");
    }

    @Test
    void getPassportNumber() {
        assertEquals(data.getPassportNumber(), "1");
    }

    @Test
    void getPassportIssueDate() {
        assertEquals(data.getPassportIssueDate(), LocalDate.now());
    }

    @Test
    void getPassportIssueBranch() {
        assertEquals(data.getPassportIssueBranch(), "a");
    }

    @Test
    void getAccount() {
        assertEquals(data.getAccount(), "a");
    }
}