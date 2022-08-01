package ru.neoflex.deal.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neoflex.deal.embeddables.ApplicationStatusHistoryDTO;
import ru.neoflex.deal.embeddables.LoanOfferDTO;
import ru.neoflex.deal.enums.ApplicationStatus;
import ru.neoflex.deal.models.Application;
import ru.neoflex.deal.repositories.ApplicationRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class OfferService {
    private final ApplicationRepository applicationRepository;
    private final SendService sendService;

    @Autowired
    public OfferService(ApplicationRepository applicationRepository, SendService sendService) {
        this.applicationRepository = applicationRepository;
        this.sendService = sendService;
    }

    public void updateApplication(LoanOfferDTO data) throws IllegalArgumentException, UnsupportedOperationException {
        log.info("API /deal/offer: Входные данные - " + data);
        Application application;
        application = applicationRepository.findById(data.getApplicationId()).orElseThrow(() -> new IllegalArgumentException(data.getApplicationId().toString()));
        log.info("API /deal/offer: По id " + data.getApplicationId() + " найденна заявка - " + application);
        application.setApplicationStatus(ApplicationStatus.PREAPPROVAL);
        log.info("API /deal/offer: Статус заявки обновлне на Preapproval");
        List<ApplicationStatusHistoryDTO> history = application.getStatusHistory();
        history.add(new ApplicationStatusHistoryDTO(ApplicationStatus.PREAPPROVAL, LocalDateTime.now(), null));
        application.setStatusHistory(history);
        log.info("API /deal/offer: Обновлено истроия статусов заявки");
        try {
            application.setAppliedOffer(data);
            log.info("API /deal/offer: Входные данные добавлены в поле appliedOffer");
        } catch (Exception e) {
            throw new UnsupportedOperationException("Не верные входные данные");
        }
        applicationRepository.save(application);
        log.info("API /deal/offer: Заявка обновлена в базе данных");
        try {
            sendService.createDocuments(application.getId());
            log.info("API /deal/offer: Отправлено сообщение на создание документов");
        } catch (Exception exception) {
            log.error("API /deal/offer: Не удалось отправить сообщение на создание документов");
        }
    }
}
