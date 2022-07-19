package ru.neoflex.deal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neoflex.deal.embeddables.ApplicationStatusHistoryDTO;
import ru.neoflex.deal.embeddables.LoanOfferDTO;
import ru.neoflex.deal.enums.ApplicationStatus;
import ru.neoflex.deal.models.Application;
import ru.neoflex.deal.repositories.ApplicationRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OfferService {
    private final ApplicationRepository applicationRepository;

    @Autowired
    public OfferService(ApplicationRepository applicationRepository){
        this.applicationRepository = applicationRepository;
    }

    public void updateApplication(LoanOfferDTO data){
        Application application = applicationRepository.findById(data.getApplicationId()).orElse(new Application());
        application.setApplicationStatus(ApplicationStatus.PREAPPROVAL);
        List<ApplicationStatusHistoryDTO> history = application.getStatusHistory();
        history.add(new ApplicationStatusHistoryDTO(ApplicationStatus.PREAPPROVAL, LocalDateTime.now(), null));
        application.setStatusHistory(history);
        application.setAppliedOffer(data);
        applicationRepository.save(application);
    }
}
