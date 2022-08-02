package ru.neoflex.gateway.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.gateway.feign.ApplicationClient;
import ru.neoflex.gateway.models.embeddables.LoanOfferDTO;

@RestController
@Slf4j
@Tag(name = "OfferController", description = "Отправляет запрос в MC Deal")
public class ApplicationOfferController {
    private final ApplicationClient applicationClient;
    @Autowired
    public ApplicationOfferController(ApplicationClient applicationClient){
        this.applicationClient=applicationClient;
    }
    @PutMapping("/application/offer")
    @Operation(
            summary = "Выбор предложения",
            description = "Отправляется POST-запрос на /deal/offer в МС deal через FeignClient"
    )
    public void sendRequest(@RequestBody @Parameter(description = "Предложение кредита") LoanOfferDTO data) {
        log.info("API /application/offer: Входные данные - " + data);
        try {
            applicationClient.sendRequest(data);
        } catch (Exception e) {
            log.error(e.toString());
        }
    }
}
