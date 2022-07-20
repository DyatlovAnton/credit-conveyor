package ru.neoflex.application.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.application.feign.OffersClient;
import ru.neoflex.application.models.LoanOfferDTO;

@RestController
@Slf4j
@Tag(name = "OfferController", description = "Отправляет запрос в MC Deal")
public class OfferController {
    private final OffersClient offersClient;

    @Autowired
    public OfferController(OffersClient offersClient) {
        this.offersClient = offersClient;
    }

    @PutMapping("/application/offer")
    @Operation(
            summary = "Выбор предложения",
            description = "Отправляется POST-запрос на /deal/offer в МС deal через FeignClient"
    )
    public void sendRequest(@RequestBody @Parameter(description = "Предложение кредита") LoanOfferDTO data) {
        log.info("API /application/offer: Входные данные - " + data);
        try {
            offersClient.sendOffer(data);
        } catch (Exception e) {
            log.error("API /application/offer: Не удалось отправить запрос в MC Deal");
        }
    }
}
