package ru.neoflex.gateway.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.gateway.feign.DealClient;
import ru.neoflex.gateway.models.embeddables.LoanOfferDTO;

@Slf4j
@RestController
@Tag(name = "Offer", description = "Обновление статуса заявки, истории статусов заявки и добавление поля appliedOffer")
public class OfferController {
    private final DealClient dealClient;

    @Autowired
    public OfferController(DealClient dealClient) {
        this.dealClient = dealClient;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/deal/offer", consumes = "application/json")
    @Operation(
            summary = "Обновление application",
            description = "Обновление статуса заявки, истории статусов заявки и добавление поля appliedOffer"
    )
    public void updateApplication(@RequestBody @Parameter(description = "Заявка на кредит") LoanOfferDTO data) {
        try {
            dealClient.updateApplication(data);
        } catch (Exception exception) {
            log.error(exception.toString());
        }
    }
}
