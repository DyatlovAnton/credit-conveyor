package ru.neoflex.deal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.deal.embeddables.LoanOfferDTO;
import ru.neoflex.deal.services.OfferService;

@RestController
public class OfferController {

    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService){
        this.offerService = offerService;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/deal/offer", consumes = "application/json")
    public void updateApplication(@RequestBody LoanOfferDTO data){
        try {
            offerService.updateApplication(data);
        }
        catch (Exception exception){

        }
    }
}
