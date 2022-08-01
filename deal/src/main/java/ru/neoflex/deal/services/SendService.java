package ru.neoflex.deal.services;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.neoflex.deal.enums.Theme;
import ru.neoflex.deal.models.Application;
import ru.neoflex.deal.repositories.ApplicationRepository;

@Service
@Slf4j
public class SendService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ApplicationRepository applicationRepository;

    @Autowired
    public SendService(KafkaTemplate<String, String> kafkaTemplate, ApplicationRepository applicationRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.applicationRepository = applicationRepository;
    }

    public void finishRegistration(Long applicationId) throws Exception {
        Application application = applicationRepository.findById(applicationId).orElseThrow(Exception::new);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("address",application.getClient().getEmail());
        jsonObject.put("theme",Theme.FINISH_REGISTRATION.toString());
        jsonObject.put("applicationId",applicationId);
        kafkaTemplate.send("finish-registration", jsonObject.toString());
    }

    public void createDocuments(Long applicationId) throws Exception {
        Application application = applicationRepository.findById(applicationId).orElseThrow(Exception::new);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("address",application.getClient().getEmail());
        jsonObject.put("theme",Theme.CREATE_DOCUMENTS.toString());
        jsonObject.put("applicationId",applicationId);
        kafkaTemplate.send("create-documents", jsonObject.toString());
    }

    public void sendDocuments(Long applicationId) throws Exception {
        Application application = applicationRepository.findById(applicationId).orElseThrow(Exception::new);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("address",application.getClient().getEmail());
        jsonObject.put("theme",Theme.SEND_DOCUMENTS.toString());
        jsonObject.put("applicationId",applicationId);
        kafkaTemplate.send("send-documents", jsonObject.toString());
    }

    public void sendSES(Long applicationId) throws Exception {
        Application application = applicationRepository.findById(applicationId).orElseThrow(Exception::new);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("address",application.getClient().getEmail());
        jsonObject.put("theme",Theme.SEND_SES.toString());
        jsonObject.put("applicationId",applicationId);
        kafkaTemplate.send("send-ses", jsonObject.toString());
    }

    public void creditIssued(Long applicationId) throws Exception {
        Application application = applicationRepository.findById(applicationId).orElseThrow(Exception::new);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("address",application.getClient().getEmail());
        jsonObject.put("theme",Theme.CREDIT_ISSUED.toString());
        jsonObject.put("applicationId",applicationId);
        kafkaTemplate.send("credit-issued", jsonObject.toString());
        log.info("API /deal/document/{applicationId}/send: Кредит выдан");
    }

    public void applicationDenied(Long applicationId) throws Exception {
        Application application = applicationRepository.findById(applicationId).orElseThrow(Exception::new);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("address",application.getClient().getEmail());
        jsonObject.put("theme",Theme.APPLICATION_DENIED.toString());
        jsonObject.put("applicationId",applicationId);
        kafkaTemplate.send("application-denied", jsonObject.toString());
        log.info("API /deal/document/{applicationId}/send: Заявка отклонена");
    }
}
