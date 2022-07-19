package ru.neoflex.deal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neoflex.deal.feign.OffersClient;
import ru.neoflex.deal.models.Application;
import ru.neoflex.deal.models.CreditDTO;
import ru.neoflex.deal.models.FinishRegistrationRequestDTO;
import ru.neoflex.deal.models.ScoringDataDTO;
import ru.neoflex.deal.repositories.ApplicationRepository;

@Service
public class CalculateService {
    private final ApplicationRepository applicationRepository;

    private final OffersClient offersClient;

    @Autowired
    public CalculateService(ApplicationRepository applicationRepository, OffersClient offersClient){
        this.applicationRepository = applicationRepository;
        this.offersClient = offersClient;
    }

    public void updateApplication(Long applicationId, FinishRegistrationRequestDTO data){
        Application application = applicationRepository.findById(applicationId).orElse(new Application());
        ScoringDataDTO scoringData = new ScoringDataDTO.Builder()
                .lastName(application.getClient().getLastName())
                .firstName(application.getClient().getFirstName())
                .middleName(application.getClient().getMiddleName())
                .gender(data.getGender())
                .birthdate(application.getClient().getBirthDate())
                .passportSeries(application.getClient().getPassport().getSeries())
                .passportNumber(application.getClient().getPassport().getNumber())
                .passportIssueDate(data.getPassportIssueDate())
                .passportIssueBranch(data.getPassportIssueBranch())
                .maritalStatus(data.getMaritalStatus())
                .dependentAmount(data.getDependentAmount())
                .employment(data.getEmployment())
                .account(data.getAccount())
                .isInsuranceEnabled(application.getAppliedOffer().getIsInsuranceEnabled())
                .isSalaryClient(application.getAppliedOffer().getIsSalaryClient())
                .term(application.getAppliedOffer().getTerm())
                .amount(application.getAppliedOffer().getRequestedAmount())
                .build();
        CreditDTO credit = offersClient.scoreCredit(scoringData);
    }
}
