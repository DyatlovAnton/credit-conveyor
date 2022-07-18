package ru.neoflex.deal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.deal.embeddables.ApplicationStatusHistoryDTO;
import ru.neoflex.deal.embeddables.LoanOfferDTO;
import ru.neoflex.deal.enums.ApplicationStatus;
import ru.neoflex.deal.models.Application;
import ru.neoflex.deal.repositories.ApplicationRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class OfferController {

    private final ApplicationRepository applicationRepository;

    @Autowired
    public OfferController(ApplicationRepository applicationRepository){
        this.applicationRepository = applicationRepository;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/deal/offer", consumes = "application/json")
    public void updateApplication(@RequestBody LoanOfferDTO data){
        Application application = applicationRepository.findById(data.getApplicationId()).orElse(new Application());
        application.setApplicationStatus(ApplicationStatus.PREAPPROVAL);
        List<ApplicationStatusHistoryDTO> history = application.getStatusHistory();
        history.add(new ApplicationStatusHistoryDTO(ApplicationStatus.PREAPPROVAL, LocalDateTime.now(), null));
        application.setStatusHistory(history);
        application.setAppliedOffer(data);
        applicationRepository.save(application);
    }
}
