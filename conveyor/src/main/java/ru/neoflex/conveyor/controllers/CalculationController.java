package ru.neoflex.conveyor.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.conveyor.mappings.CreditDTOMapper;
import ru.neoflex.conveyor.models.*;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import ru.neoflex.conveyor.services.CalculationService;

@RestController
@Slf4j
@Tag(name = "Calculation Controller", description = "Сalculates loan terms")
public class CalculationController {
    private final CalculationService calculationService;
    private final CreditDTOMapper creditDTOMapper = new CreditDTOMapper();

    public CalculationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @PostMapping("/conveyor/calculation")
    @Operation(description = "Сalculates loan terms")
    public ResponseEntity getCredit(@RequestBody @Parameter(description = "Данные для скоринга") ScoringDataDTO data) {
        log.info("API /conveyor/calculation: Входные данные: " + data);
        BigDecimal rate = calculationService.score(data);
        if (rate != null) {
            log.info("API /conveyor/offers: Скоринг успешно пройден");
            return ResponseEntity.ok(creditDTOMapper.scoringDataDTOtoCreditDTOMapping(data, rate));
        } else {
            log.error("API /conveyor/offers: Скоринг не пройден");
            return ResponseEntity.ok("Скоринг не пройден");
        }
    }
}
