package ru.neoflex.conveyor.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import ru.neoflex.conveyor.controllers.CalculationController;
import ru.neoflex.conveyor.controllers.OffersController;
import ru.neoflex.conveyor.mappings.CreditDTOMapper;
import ru.neoflex.conveyor.models.*;
import ru.neoflex.conveyor.services.CalculationService;
import ru.neoflex.conveyor.services.OffersService;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllersTest {

    private static final OffersService offersService = new OffersService();
    private static final CalculationService calculationService = new CalculationService();
    private static final CreditDTOMapper CREDIT_DTO_MAPPER = new CreditDTOMapper();
    private static final OffersController offersController = new OffersController(offersService);
    private  static final CalculationController  calculationController = new CalculationController( calculationService);
    private final LoanApplicationRequestDTO data = new LoanApplicationRequestDTO(BigDecimal.valueOf(100000), 36, "Николай", "Николаев", "Николаевич", "nnikolaev@neoflex.ru", LocalDate.of(1970, 5, 5), "4515", "123456");
    ScoringDataDTO scoringData = new ScoringDataDTO(BigDecimal.valueOf(100000), 6, "Николай", "Николаев", "Николаевич", Gender.MALE, LocalDate.of(1970, 5, 5), "4515", "123456", LocalDate.of(2020, 2, 2), "МФЦ района Советский", MaritalStatus.SINGLE, 0, new EmploymentDTO(EmploymentStatus.EMPLOYED, "7889456321", BigDecimal.valueOf(50000), Position.WORKER, 50, 15), "nnikolaev@neoflex.ru", true, true);
    ScoringDataDTO scoringData1 = new ScoringDataDTO(BigDecimal.valueOf(100000), 6, "Николай", "Николаев", "Николаевич", Gender.MALE, LocalDate.of(1970, 5, 5), "4515", "123456", LocalDate.of(2020, 2, 2), "МФЦ района Советский", MaritalStatus.SINGLE, 0, new EmploymentDTO(EmploymentStatus.UNEMPLOYED, "7889456321", BigDecimal.valueOf(50000), Position.WORKER, 50, 15), "nnikolaev@neoflex.ru", true, true);

    @BeforeAll
    static void setFields() {
        ReflectionTestUtils.setField(offersService, "baseRate", BigDecimal.valueOf(0.152));
        ReflectionTestUtils.setField(offersService, "insurance", BigDecimal.valueOf(0.015));
        ReflectionTestUtils.setField(calculationService, "baseRate", BigDecimal.valueOf(0.152));
        ReflectionTestUtils.setField(calculationService, "insurance", BigDecimal.valueOf(0.015));
    }

    @Test
    void getList() {
        List<LoanOfferDTO> list = Arrays.asList(
                new LoanOfferDTO(0L, BigDecimal.valueOf(100000), BigDecimal.valueOf(119700.64), 36, BigDecimal.valueOf(3283.35), BigDecimal.valueOf(0.112), true, true),
                new LoanOfferDTO(1L, BigDecimal.valueOf(100000), BigDecimal.valueOf(121415.69), 36, BigDecimal.valueOf(3330.99), BigDecimal.valueOf(0.122), true, false),
                new LoanOfferDTO(2L, BigDecimal.valueOf(100000), BigDecimal.valueOf(123389.45), 36, BigDecimal.valueOf(3427.48), BigDecimal.valueOf(0.142), false, true),
                new LoanOfferDTO(3L, BigDecimal.valueOf(100000), BigDecimal.valueOf(125148.06), 36, BigDecimal.valueOf(3476.33), BigDecimal.valueOf(0.152), false, false)
        );
        assertEquals(ResponseEntity.ok(list), offersController.getList(data));
        LoanApplicationRequestDTO data0 = new LoanApplicationRequestDTO(BigDecimal.valueOf(100), 36, "Николай", "Николаев", "Николаевич", "nnikolaev@neoflex.ru", LocalDate.of(1970, 5, 5), "4515", "123456");
        assertEquals(ResponseEntity.ok("Прескоринг не пройден"), offersController.getList(data0));
    }

    @Test
    void getCredit() {
        assertEquals(calculationController.getCredit(scoringData), ResponseEntity.ok(CREDIT_DTO_MAPPER.scoringDataDTOtoCreditDTOMapping(scoringData, calculationService.score(scoringData))));
        assertEquals(calculationController.getCredit(scoringData), ResponseEntity.ok(CREDIT_DTO_MAPPER.scoringDataDTOtoCreditDTOMapping(scoringData, calculationService.score(scoringData))));
        assertEquals(calculationController.getCredit(scoringData1), ResponseEntity.ok("Скоринг не пройден"));
    }
}