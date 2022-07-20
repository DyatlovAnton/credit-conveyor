package ru.neoflex.deal.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.deal.embeddables.LoanOfferDTO;
import ru.neoflex.deal.input.LoanApplicationRequestDTO;
import ru.neoflex.deal.services.ApplicationService;

import java.util.List;

@Slf4j
@RestController
@Tag(name = "Application", description = "Сохранение в БД сущностей Client и Application, получение 4-х предложений от MC Conveyor")
public class ApplicationController {
    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/deal/application")
    @Operation(
            summary = "Получение 4 предложений",
            description = "Сохранение в БД сущностей Client и Application, получение 4-х предложений от MC Conveyor"
    )
    public ResponseEntity getApplication(@RequestBody @Parameter(description = "Запрос предложения заявки на кредит") LoanApplicationRequestDTO data) {
        log.info("API /deal/application: входные данные - " + data);
        try {
            List<LoanOfferDTO> answer = applicationService.getApplication(data);
            return ResponseEntity.ok(answer);
        } catch (Exception exception) {
            log.error("API /deal/application: Не удалось рассчитать предложения по кредиту");
            return ResponseEntity.ok("Не удалось рассчитать предложения по кредиту");
        }
    }
}
