package ru.neoflex.gateway.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.gateway.feign.DealClient;
import ru.neoflex.gateway.models.database.Application;

import java.util.List;

@Slf4j
@RestController
@Tag(name = "AdminAllApplicationsController", description = "получить все заявки")
public class AdminAllApplicationsController {
    private final DealClient dealClient;

    @Autowired
    public AdminAllApplicationsController(DealClient dealClient) {
        this.dealClient = dealClient;
    }
    @GetMapping("/deal/admin/application")
    @Operation(
            summary = "Все заявки",
            description = "Получение всех заявок на кредит"
    )
    public ResponseEntity getAllApplications(){
        try{
            List<Application> list = dealClient.getAllApplications();
            log.info(list.toString());
            return ResponseEntity.ok(list);
        } catch (Exception exception){
            log.error(exception.toString());
            return ResponseEntity.ok("Не удалось получить все заявки");
        }
    }
}
