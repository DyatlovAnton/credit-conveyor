package ru.neoflex.conveyor.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.conveyor.models.*;

import org.springframework.http.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import ru.neoflex.conveyor.services.OffersService;

@RestController
@Slf4j
@Tag(name = "Offers Controller", description = "Pre-scoring and issuance of four loan offers")
public class OffersController {
    private final OffersService offersService;

    public OffersController(OffersService offersService) {
        this.offersService = offersService;
    }

    @PostMapping("/conveyor/offers")
    @Operation(description = "pre-scoring and issuance of four loan offers")
    public ResponseEntity getList(@RequestBody @Parameter(description = "Заявка на получение кредита") LoanApplicationRequestDTO data) {
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
