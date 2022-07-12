package ru.neoflex.deal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.neoflex.deal.embeddables.EmploymentDTO;
import ru.neoflex.deal.embeddables.LoanOfferDTO;
import ru.neoflex.deal.enums.Gender;
import ru.neoflex.deal.enums.MaritalStatus;
import ru.neoflex.deal.feign.OffersClient;
import ru.neoflex.deal.models.Application;
import ru.neoflex.deal.models.Credit;
import ru.neoflex.deal.models.FinishRegistrationRequestDTO;
import ru.neoflex.deal.models.ScoringDataDTO;
import ru.neoflex.deal.repositories.ApplicationRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CalculateController {

    private final ApplicationRepository applicationRepository;

    private final OffersClient offersClient;

    @Autowired
    public CalculateController(ApplicationRepository applicationRepository, OffersClient offersClient){
        this.applicationRepository = applicationRepository;
        this.offersClient = offersClient;
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
        Credit credit = offersClient.scoreCredit(scoringData);
        System.out.println(credit.getId());
    }
}
