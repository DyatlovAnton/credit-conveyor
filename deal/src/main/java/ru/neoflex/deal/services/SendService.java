package ru.neoflex.deal.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.neoflex.deal.enums.Theme;
import ru.neoflex.deal.kafka.EmailMessage;
import ru.neoflex.deal.models.Application;
import ru.neoflex.deal.repositories.ApplicationRepository;

@Service
@Slf4j
public class SendService {
    private final KafkaTemplate<String, EmailMessage> kafkaTemplate;
    private final ApplicationRepository applicationRepository;

    @Autowired
    public SendService(KafkaTemplate<String, EmailMessage> kafkaTemplate, ApplicationRepository applicationRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.applicationRepository = applicationRepository;
    }

    public void finishRegistration(Long applicationId) throws Exception {
        Application application = applicationRepository.findById(applicationId).orElseThrow(Exception::new);
        kafkaTemplate.send("finish-registration", new EmailMessage(application.getClient().getEmail(), Theme.FINISH_REGISTRATION, applicationId));
    }

    public void createDocuments(Long applicationId) throws Exception {
        Application application = applicationRepository.findById(applicationId).orElseThrow(Exception::new);
        kafkaTemplate.send("create-documents", new EmailMessage(application.getClient().getEmail(), Theme.CREATE_DOCUMENTS, applicationId));
    }

    public void sendDocuments(Long applicationId) throws Exception {
        Application application = applicationRepository.findById(applicationId).orElseThrow(Exception::new);
        kafkaTemplate.send("send-documents", new EmailMessage(application.getClient().getEmail(), Theme.SEND_DOCUMENTS, applicationId));
    }

    public void sendSES(Long applicationId) throws Exception {
        Application application = applicationRepository.findById(applicationId).orElseThrow(Exception::new);
        kafkaTemplate.send("send-ses", new EmailMessage(application.getClient().getEmail(), Theme.SEND_SES, applicationId));
    }

    public void creditIssued(Long applicationId) throws Exception {
        Application application = applicationRepository.findById(applicationId).orElseThrow(Exception::new);
        kafkaTemplate.send("credit-issued", new EmailMessage(application.getClient().getEmail(), Theme.CREDIT_ISSUED, applicationId));
        log.info("API /deal/document/{applicationId}/send: Кредит выдан");
    }

    public void applicationDenied(Long applicationId) throws Exception {
        Application application = applicationRepository.findById(applicationId).orElseThrow(Exception::new);
        kafkaTemplate.send("application-denied", new EmailMessage(application.getClient().getEmail(), Theme.APPLICATION_DENIED, applicationId));
        log.info("API /deal/document/{applicationId}/send: Заявка отклонена");
    }
}
