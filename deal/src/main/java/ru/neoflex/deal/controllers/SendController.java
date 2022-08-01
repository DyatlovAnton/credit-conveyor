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
@Tag(name = "Send", description = "Запрос на отправку документов")
public class SendController {
    private final SendService sendService;

    @Autowired
    public SendController(SendService sendService) {
        this.sendService = sendService;
    }

    @PostMapping("/deal/document/{applicationId}/send")
    @Operation(
            summary = "Запрос на отправку документов",
            description = "Запрос на отправку документов"
    )
    public void send(@PathVariable(value = "applicationId") @Min(0) @Parameter(description = "ID заявки") Long applicationId) throws Exception {
        try {
            sendService.sendDocuments(applicationId);
            log.info("API /deal/document/{applicationId}/send: Запрос на отправку документов отправлен");
        } catch (Exception exception) {
            log.error("API /deal/document/{applicationId}/send: Нет заявки с applicationID " + applicationId);
        }
    }
}
