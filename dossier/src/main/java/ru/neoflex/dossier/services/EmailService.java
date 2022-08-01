package ru.neoflex.dossier.services;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;


@Slf4j
@Service
public class EmailService {

    private static JavaMailSender emailSender;

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        EmailService.emailSender = emailSender;
    }

    public void sendEmail(String message) throws IOException, MessagingException, DocumentException {
        JSONObject jsonObject = new JSONObject(message);
        String address = jsonObject.get("address").toString();
        String theme = jsonObject.get("theme").toString();
        long applicationId = Long.parseLong(jsonObject.get("applicationId").toString());
        log.info("address: " + address);
        log.info("theme: " + theme);
        log.info("applicationId: " + applicationId);
        try {
            createFile(applicationId, theme);
            log.info("Документ успешно создан");
        } catch (Exception exception) {
            log.error("Не удалось создать документ. Ошибка: " + exception);
        }
        try {
            sendMessageWithAttachment(address, theme, applicationId);
            log.info("Письмо успешно отправлено");
        } catch (Exception exception) {
            log.error("Не удалось отправить письмо. Ошибка: " + exception);
        }
    }

    private static void createFile(long id, String theme) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(theme + ".pdf"));

        String filename1 = "fonts/Roboto-Light.ttf";
        BaseFont bf1 = BaseFont.createFont(filename1, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(bf1, 20, Font.NORMAL);

        document.open();

        String[] rows = new String[]{"Application # " + id, "Status: " + theme};
        for (String row : rows) {
            document.add(new Paragraph(row, font));
        }
        document.close();

    }

    private static void sendMessageWithAttachment(
            String to, String subject, long id) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("adiatlov.neoflex@yandex.ru");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText("Application # " + id + "\n" + "Status: " + subject);

        FileSystemResource file
                = new FileSystemResource(new File(subject + ".pdf"));
        helper.addAttachment(subject + ".pdf", file);

        emailSender.send(message);
    }
}
