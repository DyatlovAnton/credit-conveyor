package ru.neoflex.dossier.controllers;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApplicationDeniedController {
    @KafkaListener(
            topics = "application-denied",
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
