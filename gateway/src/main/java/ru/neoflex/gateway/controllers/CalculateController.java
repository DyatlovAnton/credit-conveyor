package ru.neoflex.gateway.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.gateway.feign.DealClient;
import ru.neoflex.gateway.models.input.FinishRegistrationRequestDTO;

import javax.validation.constraints.Min;

@Slf4j
@RestController
@Tag(name = "Calculate", description = "Отправка ScoringDataDTO на MC Conveyor")
public class CalculateController {
    private final DealClient dealClient;

    @Autowired
    public CalculateController(DealClient dealClient) {
        this.dealClient = dealClient;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/deal/calculate/{applicationId}", consumes = "application/json")
    @Operation(
            summary = "Отправка ScoringDataDTO",
            description = "по id находиться Создание Application, по нему создается ScoringDataDTO и отправляется на MC Conveyor"
    )
    public void updateApplication(@PathVariable(value = "applicationId") @Min(0) @Parameter(description = "ID заявки") Long applicationId, @RequestBody @Parameter(description = "Запрос завершения регистрации заявки") FinishRegistrationRequestDTO data) {
        try {
            dealClient.calculate(applicationId, data);
        } catch (Exception exception) {
            log.error(exception.toString());
        }
    }
}
