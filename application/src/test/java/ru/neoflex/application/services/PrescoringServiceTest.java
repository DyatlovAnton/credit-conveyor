package ru.neoflex.application.services;

import org.junit.jupiter.api.Test;
import ru.neoflex.application.models.LoanApplicationRequestDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PrescoringServiceTest {

    @Test
    void prescore() {
        PrescoringService service = new PrescoringService();
        LoanApplicationRequestDTO input = new LoanApplicationRequestDTO(BigDecimal.valueOf(100000), 24, "firstName", "lastName", "middleName", "user@neoflex.ru", LocalDate.of(1970,1,1),"1234","123456");
        System.out.println(service.prescore(input));
    }
}