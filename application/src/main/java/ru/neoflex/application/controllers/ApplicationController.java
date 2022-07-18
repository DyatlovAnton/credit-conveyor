package ru.neoflex.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.application.feign.OffersClient;
import ru.neoflex.application.models.LoanApplicationRequestDTO;
import ru.neoflex.application.models.LoanOfferDTO;
import ru.neoflex.application.services.PrescoringService;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ApplicationController {
    private final OffersClient offersClient;
    private final PrescoringService prescoringService;
    @Autowired
    public ApplicationController(OffersClient offersClient, PrescoringService prescoringService){
        this.offersClient = offersClient;
        this.prescoringService = prescoringService;
    }
    @PostMapping("/application")
    public List<LoanOfferDTO> getApplication(@RequestBody LoanApplicationRequestDTO data){
        boolean prescoringSuccess = prescoringService.prescore(data);
        if(prescoringSuccess){
            List<LoanOfferDTO> offers = offersClient.getOffers(data);
            return offers;
        }
        else{
            return null;
        }
    }
}
