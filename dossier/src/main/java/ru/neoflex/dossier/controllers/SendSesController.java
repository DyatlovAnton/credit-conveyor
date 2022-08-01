package ru.neoflex.dossier.controllers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.dossier.models.EmailMessage;

@RestController
public class SendSesController {
    @KafkaListener(
            topics = "send-ses",
            containerFactory = "emailMessageKafkaListenerContainerFactory")
    public void emailMessageListener(EmailMessage message) {

    }
}
