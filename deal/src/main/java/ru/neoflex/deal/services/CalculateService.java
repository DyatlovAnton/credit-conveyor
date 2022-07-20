package ru.neoflex.deal.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neoflex.deal.feign.OffersClient;
import ru.neoflex.deal.models.Application;
import ru.neoflex.deal.models.CreditDTO;
import ru.neoflex.deal.models.FinishRegistrationRequestDTO;
import ru.neoflex.deal.models.ScoringDataDTO;
import ru.neoflex.deal.repositories.ApplicationRepository;

@Slf4j
@Service
public class CalculateService {
    private final ApplicationRepository applicationRepository;

    private final OffersClient offersClient;

    @Autowired
    public CalculateService(ApplicationRepository applicationRepository, OffersClient offersClient) {
        this.applicationRepository = applicationRepository;
        this.offersClient = offersClient;
    }

    public void updateApplication(Long applicationId, FinishRegistrationRequestDTO data) throws NullPointerException, IllegalArgumentException, IllegalCallerException {
        log.info("API /deal/calculate: Входные данные - applicationId: " + applicationId + ", FinishRegistrationRequestDTO: " + data);
        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> new NullPointerException("API /deal/calculate: В базе данных в таблице applications нет элемента с id " + applicationId));
        log.info("API /deal/calculate: По id " + applicationId + " найденна заявка - " + application);
        ScoringDataDTO scoringData;
        try {
            scoringData = new ScoringDataDTO.Builder()
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
            log.info("API /deal/calculate: На основании входных данных создана сущность ScoringDataDTO - " + scoringData);
        } catch (Exception e) {
            throw new NullPointerException("API /deal/calculate: Не верные входные данные FinishRegistrationRequestDTO");
        }
        try {
            CreditDTO credit = offersClient.scoreCredit(scoringData);
            log.info("API /deal/calculate: ScoringDataDTO отправлено на mc conveyor, получен ответ - " + credit);
        } catch (Exception ex) {
            throw new NullPointerException("API /deal/calculate: Не получен ответ от mc conveyor");
        }

    }
}
