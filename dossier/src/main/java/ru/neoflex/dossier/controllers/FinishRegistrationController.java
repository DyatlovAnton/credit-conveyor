package ru.neoflex.dossier.controllers;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.dossier.models.EmailMessage;
import ru.neoflex.dossier.services.EmailService;

import javax.mail.MessagingException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Slf4j
@RestController
public class FinishRegistrationController {

    private final EmailService emailService;

    @Autowired
    public FinishRegistrationController(EmailService emailService){
        this.emailService=emailService;
    }

    @KafkaListener(
            topics = "finish-registration",
            groupId = "ConsumerGroup0")
    public void emailMessageListener(String message) throws MessagingException {
        JSONObject jsonObject = new JSONObject(message);
        String address = jsonObject.get("address").toString();
        String theme = jsonObject.get("theme").toString();
        long applicationId = Long.parseLong(jsonObject.get("applicationId").toString());
        log.info("address: " + address);
        log.info("theme: " + theme);
        log.info("applicationId: " + applicationId);
        log.info("Test Send Mail");
        emailService.sendSimpleMessage("antoshencka@gmail.com","Test","Test");
    }
}
