package ru.neoflex.deal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.deal.models.FinishRegistrationRequestDTO;
import ru.neoflex.deal.services.CalculateService;

@RestController
public class CalculateController {

    private final CalculateService calculateService;

    @Autowired
    public CalculateController(CalculateService calculateService){
        this.calculateService = calculateService;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/deal/calculate/{applicationId}", consumes = "application/json")
    public void updateApplication(@PathVariable(value = "applicationId") Long applicationId, @RequestBody FinishRegistrationRequestDTO data){
        try {
            calculateService.updateApplication(applicationId, data);
        }
        catch (Exception exception){

        }
    }
}
