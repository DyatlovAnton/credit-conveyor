package ru.neoflex.conveyor.controllers;

import org.springframework.web.bind.annotation.*;
import ru.neoflex.conveyor.models.*;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import ru.neoflex.conveyor.services.CalculationService;

@RestController
@Slf4j
public class CalculationController {

    private final CalculationService calculationService;

    public CalculationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @PostMapping("/conveyor/calculation")
    public ResponseEntity getCredit(@RequestBody ScoringDataDTO data) {
        log.info("API /conveyor/calculation: Входные данные: " + data);
        BigDecimal rate = calculationService.score(data);
        if (rate != null) {
            log.info("API /conveyor/offers: Скоринг успешно пройден");
            return ResponseEntity.ok(calculationService.scoreCreditDTO(data, rate));
        } else {
            log.info("API /conveyor/offers: Скоринг не пройден");
            return ResponseEntity.ok("Скоринг не пройден");
        }
    }
}
