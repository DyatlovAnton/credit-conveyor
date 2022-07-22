package ru.neoflex.deal.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.neoflex.deal.embeddables.LoanOfferDTO;
import ru.neoflex.deal.services.CalculateService;
import ru.neoflex.deal.services.OfferService;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OfferController.class)
class OfferControllerTest {

    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    OfferService offerService;

    @Autowired
    MockMvc mockMvc;
    @Test
    void updateApplication() throws Exception {
        LoanOfferDTO data = new LoanOfferDTO(1L, BigDecimal.valueOf(100000), BigDecimal.valueOf(132553.79), 60, BigDecimal.valueOf(2184.23), BigDecimal.valueOf(0.112), true, true);

        OfferController offerController = mock(OfferController.class);
        doNothing().when(offerController).updateApplication(data);

        System.out.println(objectMapper.writeValueAsString(data));

        mockMvc.perform(put("/deal/offer")
                        .content(objectMapper.writeValueAsString(data))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}