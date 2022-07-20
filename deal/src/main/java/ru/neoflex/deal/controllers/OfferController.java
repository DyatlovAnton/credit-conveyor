package ru.neoflex.deal.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.deal.embeddables.LoanOfferDTO;
import ru.neoflex.deal.services.OfferService;

@Slf4j
@RestController
@Tag(name = "Offer", description = "Обновление статуса заявки, истории статусов заявки и добавление поля appliedOffer")
public class OfferController {

    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/deal/offer", consumes = "application/json")
    @Operation(
            summary = "Обновление application",
            description = "Обновление статуса заявки, истории статусов заявки и добавление поля appliedOffer"
    )
    public void updateApplication(@RequestBody @Parameter(description = "Заявка на кредит") LoanOfferDTO data) {
        try {
            offerService.updateApplication(data);
        } catch (IllegalArgumentException exception) {
            log.error("API /deal/offer: В базе данных в таблице applications нет элемента с id " + exception);
        } catch (UnsupportedOperationException exception) {
            log.error("API /deal/offer: " + exception);
        }
    }
}
