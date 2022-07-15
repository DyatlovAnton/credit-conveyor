package ru.neoflex.conveyor.controllers;

import org.springframework.web.bind.annotation.*;
import ru.neoflex.conveyor.models.*;

import org.springframework.http.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import ru.neoflex.conveyor.services.OffersService;

@RestController
@Slf4j
public class OffersController {
    private final OffersService offersService;

    public OffersController(OffersService offersService) {
        this.offersService = offersService;
    }

    @PostMapping("/conveyor/offers")
    public ResponseEntity getList(@RequestBody LoanApplicationRequestDTO data) {
        log.info("API /conveyor/offers: Входные данные: " + data);
        if (offersService.prescore(data)) {
            log.info("API /conveyor/offers: Прескоринг пройден успешно");
            return ResponseEntity.ok(offersService.getLoanOfferList(data));
        } else {
            log.error("API /conveyor/offers: Прескоринг не пройден");
            return ResponseEntity.ok("Прескоринг не пройден");
        }
    }
}
