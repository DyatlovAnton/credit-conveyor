package ru.neoflex.deal.models;

import org.junit.jupiter.api.Test;
import ru.neoflex.deal.embeddables.AdditionalServices;
import ru.neoflex.deal.embeddables.ApplicationStatusHistoryDTO;
import ru.neoflex.deal.embeddables.PaymentScheduleElement;
import ru.neoflex.deal.enums.ApplicationStatus;
import ru.neoflex.deal.enums.CreditStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    @Test
    void testApplication(){
        List<ApplicationStatusHistoryDTO> list = Arrays.asList(new ApplicationStatusHistoryDTO(ApplicationStatus.APPROVED, LocalDateTime.now(), ApplicationStatus.PREAPPROVAL));
        Application application=new Application.Builder()
                .credit(new CreditDTO.Builder()
                        .amount(BigDecimal.valueOf(100000))
                        .term(36)
                        .monthlyPayment(BigDecimal.valueOf(8000))
                        .rate(BigDecimal.valueOf(0.1))
                        .psk(BigDecimal.valueOf(0.1))
                        .paymentSchedule(Arrays.asList(new PaymentScheduleElement(1, LocalDate.now(), BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE)))
                        .additionalServices(new AdditionalServices(true,true))
                        .creditStatus(CreditStatus.CALCULATED)
                        .build())
                .applicationStatus(ApplicationStatus.APPROVED)
                .creationDate(LocalDate.now())
                .signDate(LocalDate.now())
                .sesCode("SES code")
                .statusHistory(Arrays.asList(new ApplicationStatusHistoryDTO(ApplicationStatus.APPROVED, LocalDateTime.now(), ApplicationStatus.PREAPPROVAL)))
                .build();
        application.setCredit(new CreditDTO.Builder()
                .amount(BigDecimal.valueOf(100000))
                .term(36)
                .monthlyPayment(BigDecimal.valueOf(8000))
                .rate(BigDecimal.valueOf(0.1))
                .psk(BigDecimal.valueOf(0.1))
                .paymentSchedule(Arrays.asList(new PaymentScheduleElement(1, LocalDate.now(), BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE)))
                .additionalServices(new AdditionalServices(true,true))
                .creditStatus(CreditStatus.CALCULATED)
                .build());
        application.setApplicationStatus(ApplicationStatus.APPROVED);
        application.setCreationDate(LocalDate.now());
        application.setSignDate(LocalDate.now());
        application.setSesCode("SES code");
        System.out.println(application.getCredit());
        System.out.println(application.getApplicationStatus());
        System.out.println(application.getCreationDate());
        System.out.println(application.getSignDate());
        System.out.println(application.getSesCode());
    }
}