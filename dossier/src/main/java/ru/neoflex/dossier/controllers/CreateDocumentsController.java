package ru.neoflex.dossier.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;

@RestController
@Slf4j
public class CreateDocumentsController {
    @KafkaListener(
            topics = "create-documents",
            groupId = "ConsumerGroup0")
    public void emailMessageListener(String message) {
        JSONObject jsonObject = new JSONObject(message);
        String address = jsonObject.get("address").toString();
        String theme = jsonObject.get("theme").toString();
        long applicationId = Long.parseLong(jsonObject.get("applicationId").toString());
        log.info("address: " + address);
        log.info("theme: " + theme);
        log.info("applicationId: " + applicationId);
    }
}
