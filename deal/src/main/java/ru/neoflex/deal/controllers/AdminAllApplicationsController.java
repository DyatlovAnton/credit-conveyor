package ru.neoflex.deal.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.deal.services.AdminService;

@Slf4j
@RestController
@Tag(name = "AdminAllApplicationsController", description = "получить все заявки")
public class AdminAllApplicationsController {
    private final AdminService adminService;

    @Autowired
    public AdminAllApplicationsController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/deal/admin/application")
    @Operation(
            summary = "Все заявки",
            description = "Получение всех заявок на кредит"
    )
    public ResponseEntity getAllApplications(){
        try{
            return ResponseEntity.ok(adminService.getAllApplications());
        } catch (Exception exception){
            log.error("Не удалось получить все заявки. Ошибка: "+exception);
            return ResponseEntity.ok("Не удалось получить все заявки");
        }
    }
}
