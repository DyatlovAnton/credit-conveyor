package ru.neoflex.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.application.feign.OffersClient;
import ru.neoflex.application.models.LoanOfferDTO;

@RestController
public class OfferController {
    private final OffersClient offersClient;
    @Autowired
    public OfferController(OffersClient offersClient){
        this.offersClient = offersClient;
    }
    @PutMapping("/application/offer")
    public void sendRequest(@RequestBody LoanOfferDTO data){
        offersClient.sendOffer(data);
    }
}
