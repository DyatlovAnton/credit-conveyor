package ru.neoflex.deal.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.deal.models.Application;
import ru.neoflex.deal.services.AdminService;

import javax.validation.constraints.Min;

@Slf4j
@RestController
@Tag(name = "AdminApplicationIdController", description = "получить заявку по ID")
public class AdminApplicationIdController {
    private final AdminService adminService;

    @Autowired
    public AdminApplicationIdController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/deal/admin/application/{applicationId}")
    @Operation(
            summary = "Заявка по ID",
            description = "Получение заявки на кредит по её ID"
    )
    public ResponseEntity getApplication(@PathVariable(value = "applicationId") @Min(0) @Parameter(description = "ID заявки") Long applicationId){
        log.info("Входные данные - applicationID: "+applicationId.toString());
        try{
            Application application = adminService.getApplicationById(applicationId);
            log.info(application.toString());
            return ResponseEntity.ok(application);
        } catch (Exception exception){
            log.error("Нет заявки с ID "+ applicationId);
            return ResponseEntity.ok("Нет заявки с ID "+ applicationId);
        }
    }
}
