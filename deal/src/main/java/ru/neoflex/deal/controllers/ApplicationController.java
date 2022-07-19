package ru.neoflex.deal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.deal.embeddables.LoanOfferDTO;
import ru.neoflex.deal.input.LoanApplicationRequestDTO;
import ru.neoflex.deal.services.ApplicationService;

import java.util.List;

@RestController
public class ApplicationController {
    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService){
        this.applicationService = applicationService;
    }

    @PostMapping("/deal/application")
    public ResponseEntity getApplication(@RequestBody LoanApplicationRequestDTO data){
        try {
            List<LoanOfferDTO> answer = applicationService.getApplication(data);
            return ResponseEntity.ok(answer);
        }
        catch (Exception exception){
            return ResponseEntity.ok("Неверные входные данные");
        }
    }
}
