package ru.neoflex.deal.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.neoflex.deal.embeddables.LoanOfferDTO;
import ru.neoflex.deal.input.LoanApplicationRequestDTO;
import ru.neoflex.deal.services.ApplicationService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ApplicationController.class)
class ApplicationControllerTest {

    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    ApplicationService applicationService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getApplication() throws Exception {
        LoanApplicationRequestDTO input = new LoanApplicationRequestDTO(BigDecimal.valueOf(100000), 60, "firstName", "lastName", "middleName", "user@neoflex.ru", LocalDate.of(1970,1,1),"1234","123456");
        List<LoanOfferDTO> output = Arrays.asList(
                new LoanOfferDTO(1L, BigDecimal.valueOf(100000), BigDecimal.valueOf(132553.79), 60, BigDecimal.valueOf(2184.23), BigDecimal.valueOf(0.112), true, true),
                new LoanOfferDTO(1L, BigDecimal.valueOf(100000), BigDecimal.valueOf(135573.88), 60, BigDecimal.valueOf(2234.56), BigDecimal.valueOf(0.122), true, false),
                new LoanOfferDTO(1L, BigDecimal.valueOf(100000), BigDecimal.valueOf(140232.41), 60, BigDecimal.valueOf(2337.21), BigDecimal.valueOf(0.142), false, true),
                new LoanOfferDTO(1L, BigDecimal.valueOf(100000), BigDecimal.valueOf(143370.25), 60, BigDecimal.valueOf(2389.5), BigDecimal.valueOf(0.152), false, false)
        );
        ApplicationController applicationController = new ApplicationController(applicationService);

        Mockito.when(applicationService.getApplication(input)).thenReturn(output);

        mockMvc.perform(post("/deal/application")
                        .content(objectMapper.writeValueAsString(input))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void getError() throws Exception {
        LoanApplicationRequestDTO input = new LoanApplicationRequestDTO(BigDecimal.valueOf(100000), 60, "firstName", "lastName", "middleName", "user@neoflex.ru", LocalDate.of(1970,1,1),"1234","123456");
        ApplicationController applicationController = new ApplicationController(applicationService);

        Mockito.when(applicationService.getApplication(input)).thenThrow(new IllegalArgumentException());

        mockMvc.perform(post("/deal/application")
                        .content(objectMapper.writeValueAsString(input))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}