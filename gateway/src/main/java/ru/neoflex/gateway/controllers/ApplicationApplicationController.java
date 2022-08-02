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
import ru.neoflex.gateway.feign.ApplicationClient;
import ru.neoflex.gateway.models.embeddables.LoanOfferDTO;
import ru.neoflex.gateway.models.input.LoanApplicationRequestDTO;

import java.util.List;

@RestController
@Slf4j
@Tag(name="ApplicationApplicationController", description="4 предложения кредита")
public class ApplicationApplicationController {
    private final ApplicationClient applicationClient;
    @Autowired
    public ApplicationApplicationController(ApplicationClient applicationClient){
        this.applicationClient=applicationClient;
    }
    @PostMapping("/application")
    @Operation(
            summary = "Выдает 4 предложения крелита",
            description = "Прескоринг + запрос на расчёт возможных условий кредита"
    )
    public ResponseEntity getApplication(@RequestBody @Parameter(description = "Заявка на получение кредита") LoanApplicationRequestDTO data){
        log.info("API /application: Входные данные - "+data);
        try {
            List<LoanOfferDTO> list = applicationClient.getLoanOfferDTO(data);
            for(int i=0; i<4; i++){
                log.info("API /application: "+i+"-е предложение - "+list.get(i));
            }
            return ResponseEntity.ok(list);
        } catch (Exception exception){
            log.error(exception.toString());
            return ResponseEntity.ok(exception.toString());
        }
    }
}
