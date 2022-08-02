package ru.neoflex.gateway.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.gateway.feign.DealClient;

import javax.validation.constraints.Min;

@Slf4j
@RestController
@Tag(name = "Sign", description = "Запрос на подписание документов")
public class SignController {
    private final DealClient dealClient;

    @Autowired
    public SignController(DealClient dealClient) {
        this.dealClient = dealClient;
    }

    @PostMapping("/deal/document/{applicationId}/sign")
    @Operation(
            summary = "Запрос на подписание документов",
            description = "Запрос на подписание документов"
    )
    public void send(@PathVariable(value = "applicationId") @Min(0) @Parameter(description = "ID заявки") Long applicationId) {
        try {
            dealClient.sign(applicationId);
            log.info("API /deal/document/{applicationId}/sign: Регистрация закончена");
        } catch (Exception exception) {
            log.error(exception.toString());
        }
    }
}
