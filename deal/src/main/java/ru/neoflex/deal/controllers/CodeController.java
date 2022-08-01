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
@Tag(name = "Code", description = "Подписание документов")
public class CodeController {
    private final SendService sendService;

    @Autowired
    public CodeController(SendService sendService) {
        this.sendService = sendService;
    }

    @PostMapping("/deal/document/{applicationId}/code")
    @Operation(
            summary = "Подписание документов",
            description = "Подписание документов"
    )
    public void send(@PathVariable(value = "applicationId") @Min(0) @Parameter(description = "ID заявки") Long applicationId) throws Exception {
        try {
            sendService.sendSES(applicationId);
            log.info("API /deal/document/{applicationId}/code: Документы подписаны");
        } catch (Exception exception) {
            log.error("API /deal/document/{applicationId}/code: Нет заявки с applicationID " + applicationId);
        }
    }
}
