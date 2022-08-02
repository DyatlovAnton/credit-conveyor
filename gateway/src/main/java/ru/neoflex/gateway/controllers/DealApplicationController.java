package ru.neoflex.gateway.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.gateway.feign.DealClient;
import ru.neoflex.gateway.models.embeddables.LoanOfferDTO;
import ru.neoflex.gateway.models.input.LoanApplicationRequestDTO;

import java.util.List;

@Slf4j
@RestController
@Tag(name = "DealApplicationController", description = "Сохранение в БД сущностей Client и Application, получение 4-х предложений от MC Conveyor")
public class DealApplicationController {
    private final DealClient dealClient;

    @Autowired
    public DealApplicationController(DealClient dealClient) {
        this.dealClient=dealClient;
    }
    @PostMapping("/deal/application")
    @Operation(
            summary = "Получение 4 предложений",
            description = "Сохранение в БД сущностей Client и Application, получение 4-х предложений от MC Conveyor"
    )
    public ResponseEntity getApplication(@RequestBody @Parameter(description = "Запрос предложения заявки на кредит") LoanApplicationRequestDTO data) {
        log.info("API /deal/application: входные данные - " + data);
        try {
            List<LoanOfferDTO> answer = dealClient.getApplications(data);
            log.info(answer.toString());
            return ResponseEntity.ok(answer);
        } catch (Exception exception) {
            log.error(exception.toString());
            return ResponseEntity.ok(exception.toString());
        }
    }
}
