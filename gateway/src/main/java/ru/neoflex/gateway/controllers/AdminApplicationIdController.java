package ru.neoflex.gateway.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.gateway.feign.DealClient;
import ru.neoflex.gateway.models.database.Application;

import javax.validation.constraints.Min;

@Slf4j
@RestController
@Tag(name = "AdminApplicationIdController", description = "получить заявку по ID")
public class AdminApplicationIdController {
    private final DealClient dealClient;

    @Autowired
    public AdminApplicationIdController(DealClient dealClient) {
        this.dealClient = dealClient;
    }
    @GetMapping("/deal/admin/application/{applicationId}")
    @Operation(
            summary = "Заявка по ID",
            description = "Получение заявки на кредит по её ID"
    )
    public ResponseEntity getApplication(@PathVariable(value = "applicationId") @Min(0) @Parameter(description = "ID заявки") Long applicationId){
        log.info("Входные данные - applicationID: "+applicationId.toString());
        try{
            Application application=dealClient.getApplicationById(applicationId);
            log.info(application.toString());
            return ResponseEntity.ok(application);
        } catch (Exception exception){
            log.error(exception.toString());
            return ResponseEntity.ok(exception.toString());
        }
    }
}
