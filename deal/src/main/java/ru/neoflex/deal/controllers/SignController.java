package ru.neoflex.deal.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.deal.services.SendService;

import javax.validation.constraints.Min;

@Slf4j
@RestController
@Tag(name = "Sign", description = "Запрос на подписание документов")
public class SignController {
    private final SendService sendService;

    @Autowired
    public SignController(SendService sendService) {
        this.sendService = sendService;
    }

    @PostMapping("/deal/document/{applicationId}/sign")
    @Operation(
            summary = "Запрос на подписание документов",
            description = "Запрос на подписание документов"
    )
    public void send(@PathVariable(value = "applicationId") @Min(0) @Parameter(description = "ID заявки") Long applicationId) throws Exception {
        try {
            sendService.finishRegistration(applicationId);
            log.info("API /deal/document/{applicationId}/sign: Регистрация закончена");
        } catch (Exception exception) {
            log.error("API /deal/document/{applicationId}/sign: Нет заявки с applicationID " + applicationId);
        }
    }
}
