package ru.neoflex.dossier.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.dossier.services.EmailService;

@Slf4j
@RestController
@Tag(name = "KafkaListenerController", description = "Обработка сообщений из Кафки и отправка писем")
public class KafkaListenerController {
    private final EmailService emailService;

    @Autowired
    public KafkaListenerController(EmailService emailService) {
        this.emailService = emailService;
    }

    @Operation(
            summary = "Обработка сообщений из Кафки, отправка писем",
            description = "Формирование письма и документов и отправка письма на почту Клиенту по получению сообщения в Кафке"
    )
    @KafkaListener(
            topics = {"application-denied", "create-documents", "credit-issued", "finish-registration", "send-documents", "send-ses"},
            groupId = "ConsumerGroup0")
    public void emailMessageListener(@Parameter(description = "Сообщение из Кафки") String message) {
        log.info("Входные данные: " + message);
        try {
            emailService.sendEmail(message);
        } catch (Exception exception) {
            log.error("Не верные входные данные. Ошибка: " + exception);
        }
    }
}
