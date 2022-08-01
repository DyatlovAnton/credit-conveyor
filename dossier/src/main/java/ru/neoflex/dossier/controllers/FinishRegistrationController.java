package ru.neoflex.dossier.controllers;

import lombok.extern.slf4j.Slf4j;
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
            containerFactory = "emailMessageKafkaListenerContainerFactory")
    public void emailMessageListener(EmailMessage message) throws MessagingException {
        try {
            File myObj = new File("filename.txt");
            if (myObj.createNewFile()) {
                log.info("File created: " + myObj.getName());
            } else {
                log.info("File already exists.");
            }
        } catch (IOException e) {
            log.error("An error occurred.");
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter("filename.txt");
            myWriter.write("Application ID: "+message.getApplicationId()+"\nAction: "+message.getTheme().toString();
            myWriter.close();
            log.info("Successfully wrote to the file.");
        } catch (IOException e) {
            log.error("An error occurred.");
            e.printStackTrace();
        }
        emailService.sendMessageWithAttachment(message.getAddress(),"Finish registration", "Finish registration","filename.txt");
    }
}
