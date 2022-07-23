package ru.neoflex.deal.embeddables;

import org.junit.jupiter.api.Test;
import ru.neoflex.deal.enums.ApplicationStatus;
import ru.neoflex.deal.models.Application;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationStatusHistoryDTOTest {

    ApplicationStatusHistoryDTO history = new ApplicationStatusHistoryDTO(ApplicationStatus.APPROVED, LocalDateTime.now(), ApplicationStatus.PREAPPROVAL);
    ApplicationStatusHistoryDTO history2 = new ApplicationStatusHistoryDTO();
    @Test
    void testToString() {
        System.out.println(history);
    }

    @Test
    void getApplicationStatus() {
        System.out.println(history.getApplicationStatus());
    }

    @Test
    void getTime() {
        System.out.println(history.getTime());
    }

    @Test
    void getChangeType() {
        System.out.println(history.getChangeType());
    }
}