package ru.neoflex.application.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.neoflex.application.feign.OffersClient;
import ru.neoflex.application.models.LoanApplicationRequestDTO;
import ru.neoflex.application.models.LoanOfferDTO;
import ru.neoflex.application.services.PrescoringService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ApplicationControllerTest {


    @Mock
    OffersClient offersClient;

    @Mock
    PrescoringService prescoringService;


    LoanApplicationRequestDTO input = new LoanApplicationRequestDTO(BigDecimal.valueOf(100000L), 24, "firstName", "lastName", "middleName", "user@neoflex.ru", LocalDate.of(1970,1,1),"1234","123456");
    List<LoanOfferDTO> output = Arrays.asList(
            new LoanOfferDTO(1L, BigDecimal.valueOf(100000), BigDecimal.valueOf(132553.79), 60, BigDecimal.valueOf(2184.23), BigDecimal.valueOf(0.112), true, true),
            new LoanOfferDTO(1L, BigDecimal.valueOf(100000), BigDecimal.valueOf(135573.88), 60, BigDecimal.valueOf(2234.56), BigDecimal.valueOf(0.122), true, false),
            new LoanOfferDTO(1L, BigDecimal.valueOf(100000), BigDecimal.valueOf(140232.41), 60, BigDecimal.valueOf(2337.21), BigDecimal.valueOf(0.142), false, true),
            new LoanOfferDTO(1L, BigDecimal.valueOf(100000), BigDecimal.valueOf(143370.25), 60, BigDecimal.valueOf(2389.5), BigDecimal.valueOf(0.152), false, false)
    );
    @Test
    void getApplication() {
        Mockito.when(prescoringService.prescore(input)).thenThrow(NullPointerException.class);
        ApplicationController controller = new ApplicationController(offersClient, prescoringService);
        assertEquals(ResponseEntity.ok("Прескоринг не пройден"),controller.getApplication(input));
    }
    @Test
    void getApplication1() {
        Mockito.when(prescoringService.prescore(input)).thenReturn(true);
        Mockito.when(offersClient.getOffers(input)).thenThrow(NullPointerException.class);
        ApplicationController controller = new ApplicationController(offersClient, prescoringService);
        assertEquals(ResponseEntity.ok("Не удалось получить предложения кредита"),controller.getApplication(input));
    }
    @Test
    void getApplication2(){
        Mockito.when(prescoringService.prescore(input)).thenReturn(true);
        Mockito.when(offersClient.getOffers(input)).thenReturn(output);
        ApplicationController controller = new ApplicationController(offersClient, prescoringService);
        assertEquals(ResponseEntity.ok(output),controller.getApplication(input));
    }
}