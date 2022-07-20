package ru.neoflex.application.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.application.feign.OffersClient;
import ru.neoflex.application.models.LoanApplicationRequestDTO;
import ru.neoflex.application.models.LoanOfferDTO;
import ru.neoflex.application.services.PrescoringService;

import java.util.List;

@RestController
@Slf4j
@Tag(name="ApplicationController", description="4 предложения кредита")
public class ApplicationController {
    private final OffersClient offersClient;
    private final PrescoringService prescoringService;
    @Autowired
    public ApplicationController(OffersClient offersClient, PrescoringService prescoringService){
        this.offersClient = offersClient;
        this.prescoringService = prescoringService;
    }
    @PostMapping("/application")
    @Operation(
            summary = "Выдает 4 предложения крелита",
            description = "Прескоринг + запрос на расчёт возможных условий кредита"
    )
    public ResponseEntity getApplication(@RequestBody @Parameter(description = "Заявка на получение кредита") LoanApplicationRequestDTO data){
        log.info("API /application: Входные данные - "+data);
        boolean prescoringSuccess;
        try{
            prescoringSuccess = prescoringService.prescore(data);
        }
        catch (Exception e){
            prescoringSuccess = false;
            log.error("API /application: Неверные входные данные");
        }
        if(prescoringSuccess){
            log.error("API /application: Прескоринг успешно пройден");
            try {
                List<LoanOfferDTO> list = offersClient.getOffers(data);
                for(int i=0; i<4; i++){
                    log.info("API /application: "+i+"-е предложение - "+list.get(i));
                }
                return ResponseEntity.ok(list);
            }
            catch (Exception e){
                log.error("API /application: Не получен ответ от MC Deal");
                return ResponseEntity.ok("Не удалось получить предложения кредита");
            }
        }
        else{
            log.error("API /application: Прескоринг не пройден");
            return ResponseEntity.ok("Прескоринг не пройден");
        }
    }
}
