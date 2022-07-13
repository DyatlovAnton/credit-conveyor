package ru.neoflex.deal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.deal.embeddables.EmploymentDTO;
import ru.neoflex.deal.enums.Gender;
import ru.neoflex.deal.enums.MaritalStatus;
import ru.neoflex.deal.feign.OffersClient;
import ru.neoflex.deal.models.Application;
import ru.neoflex.deal.models.CreditDTO;
import ru.neoflex.deal.models.FinishRegistrationRequestDTO;
import ru.neoflex.deal.models.ScoringDataDTO;
import ru.neoflex.deal.repositories.ApplicationRepository;
import ru.neoflex.deal.repositories.CreditRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
public class CalculateController {

    private final ApplicationRepository applicationRepository;

    private final OffersClient offersClient;

    private final CreditRepository creditRepository;

    @Autowired
    public CalculateController(ApplicationRepository applicationRepository, OffersClient offersClient, CreditRepository creditRepository){
        this.applicationRepository = applicationRepository;
        this.offersClient = offersClient;
        this.creditRepository = creditRepository;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/deal/calculate/{applicationId}", consumes = "application/json")
    public void updateApplication(@PathVariable(value = "applicationId") Long applicationId, @RequestBody FinishRegistrationRequestDTO data){
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
