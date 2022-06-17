package ru.neoflex.conveyor.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import ru.neoflex.conveyor.models.*;
import ru.neoflex.conveyor.services.CalculationService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CalculationServiceTest {

    RoundingMode rm = RoundingMode.HALF_UP;

    private static final CalculationService calculationService = new CalculationService();
    ScoringDataDTO data = new ScoringDataDTO(BigDecimal.valueOf(100000), 6, "Николай", "Николаев", "Николаевич", Gender.MALE, LocalDate.of(1970, 5, 5), "4515", "123456", LocalDate.of(2020, 2, 2), "МФЦ района Советский", MaritalStatus.SINGLE, 0, new EmploymentDTO(EmploymentStatus.EMPLOYED, "7889456321", BigDecimal.valueOf(50000), Position.WORKER, 50, 15), "nnikolaev@neoflex.ru", true, true);

    @BeforeAll
    static void setFields() {
        ReflectionTestUtils.setField(calculationService, "baseRate", BigDecimal.valueOf(0.152));
        ReflectionTestUtils.setField(calculationService, "insurance", BigDecimal.valueOf(0.015));
    }

    @Test
    void score() {
        assertEquals(BigDecimal.valueOf(0.109), calculationService.score(data));
        ScoringDataDTO test0 = new ScoringDataDTO(BigDecimal.valueOf(100000), 6, "Николай", "Николаев", "Николаевич", Gender.MALE, LocalDate.of(1970, 5, 5), "4515", "123456", LocalDate.of(2020, 2, 2), "МФЦ района Советский", MaritalStatus.SINGLE, 0, new EmploymentDTO(EmploymentStatus.UNEMPLOYED, "7889456321", BigDecimal.valueOf(50000), Position.WORKER, 50, 15), "nnikolaev@neoflex.ru", true, true);
        assertNull(calculationService.score(test0));
        ScoringDataDTO test1 = new ScoringDataDTO(BigDecimal.valueOf(100000), 6, "Николина", "Николаева", "Николаевна", Gender.FEMALE, LocalDate.of(1970, 5, 5), "4515", "123456", LocalDate.of(2020, 2, 2), "МФЦ района Советский", MaritalStatus.MARRIED, 0, new EmploymentDTO(EmploymentStatus.SELF_EMPLOYED, "7889456321", BigDecimal.valueOf(50000), Position.MID_MANAGER, 50, 15), "nnikolaev@neoflex.ru", true, true);
        assertEquals(BigDecimal.valueOf(0.105), calculationService.score(test1));
        ScoringDataDTO test2 = new ScoringDataDTO(BigDecimal.valueOf(100000), 6, "Николина", "Николаева", "Николаевна", Gender.NON_BINARY, LocalDate.of(1970, 5, 5), "4515", "123456", LocalDate.of(2020, 2, 2), "МФЦ района Советский", MaritalStatus.DIVORCED, 3, new EmploymentDTO(EmploymentStatus.BUSINESS_OWNER, "7889456321", BigDecimal.valueOf(50000), Position.TOP_MANAGER, 50, 15), "nnikolaev@neoflex.ru", true, true);
        assertEquals(BigDecimal.valueOf(0.116), calculationService.score(test2));
        ScoringDataDTO test3 = new ScoringDataDTO(BigDecimal.valueOf(99900000), 6, "Николай", "Николаев", "Николаевич", Gender.MALE, LocalDate.of(1970, 5, 5), "4515", "123456", LocalDate.of(2020, 2, 2), "МФЦ района Советский", MaritalStatus.SINGLE, 0, new EmploymentDTO(EmploymentStatus.EMPLOYED, "7889456321", BigDecimal.valueOf(1000), Position.WORKER, 50, 15), "nnikolaev@neoflex.ru", true, true);
        assertNull(calculationService.score(test3));
        ScoringDataDTO test4 = new ScoringDataDTO(BigDecimal.valueOf(100000), 6, "Николай", "Николаев", "Николаевич", Gender.MALE, LocalDate.of(1940, 5, 5), "4515", "123456", LocalDate.of(2020, 2, 2), "МФЦ района Советский", MaritalStatus.SINGLE, 0, new EmploymentDTO(EmploymentStatus.EMPLOYED, "7889456321", BigDecimal.valueOf(50000), Position.WORKER, 50, 15), "nnikolaev@neoflex.ru", true, true);
        assertNull(calculationService.score(test4));
        ScoringDataDTO test5 = new ScoringDataDTO(BigDecimal.valueOf(100000), 6, "Николай", "Николаев", "Николаевич", Gender.MALE, LocalDate.of(1970, 5, 5), "4515", "123456", LocalDate.of(2020, 2, 2), "МФЦ района Советский", MaritalStatus.SINGLE, 0, new EmploymentDTO(EmploymentStatus.EMPLOYED, "7889456321", BigDecimal.valueOf(50000), Position.WORKER, 5, 1), "nnikolaev@neoflex.ru", true, true);
        assertNull(calculationService.score(test5));
    }

    @Test
    void scoreCreditDTO() {
        CreditDTO creditDTO = new CreditDTO(BigDecimal.valueOf(103203.12), 6, BigDecimal.valueOf(17200.52), BigDecimal.valueOf(0.109), BigDecimal.valueOf(10.9).setScale(3, rm), true, true,
                Arrays.asList(
                        new PaymentScheduleElement(1, LocalDate.now().plusMonths(1), BigDecimal.valueOf(17200.52), BigDecimal.valueOf(908.33), BigDecimal.valueOf(16292.19), BigDecimal.valueOf(83707.81)),
                        new PaymentScheduleElement(2, LocalDate.now().plusMonths(2), BigDecimal.valueOf(17200.52), BigDecimal.valueOf(760.35), BigDecimal.valueOf(16440.17), BigDecimal.valueOf(67267.64)),
                        new PaymentScheduleElement(3, LocalDate.now().plusMonths(3), BigDecimal.valueOf(17200.52), BigDecimal.valueOf(611.01), BigDecimal.valueOf(16589.51), BigDecimal.valueOf(50678.13)),
                        new PaymentScheduleElement(4, LocalDate.now().plusMonths(4), BigDecimal.valueOf(17200.52), BigDecimal.valueOf(460.33), BigDecimal.valueOf(16740.19), BigDecimal.valueOf(33937.94)),
                        new PaymentScheduleElement(5, LocalDate.now().plusMonths(5), BigDecimal.valueOf(17200.52), BigDecimal.valueOf(308.27), BigDecimal.valueOf(16892.25), BigDecimal.valueOf(17045.69)),
                        new PaymentScheduleElement(6, LocalDate.now().plusMonths(6), BigDecimal.valueOf(17200.52), BigDecimal.valueOf(154.83), BigDecimal.valueOf(17045.69), BigDecimal.valueOf(0).setScale(2, rm))
                ));
        assertEquals(creditDTO, calculationService.scoreCreditDTO(data, calculationService.score(data)));
    }
}