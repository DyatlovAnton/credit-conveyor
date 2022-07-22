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
import ru.neoflex.deal.embeddables.EmploymentDTO;
import ru.neoflex.deal.enums.EmploymentStatus;
import ru.neoflex.deal.enums.Gender;
import ru.neoflex.deal.enums.MaritalStatus;
import ru.neoflex.deal.enums.Position;
import ru.neoflex.deal.models.FinishRegistrationRequestDTO;
import ru.neoflex.deal.services.ApplicationService;
import ru.neoflex.deal.services.CalculateService;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CalculateController.class)
class CalculateControllerTest {
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    CalculateService calculateService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void updateApplication() throws Exception {
        Long applicationId = 1L;
        FinishRegistrationRequestDTO data = new FinishRegistrationRequestDTO(Gender.MALE, MaritalStatus.SINGLE, 0, LocalDate.of(2016, 4, 1), "МФЦ",
                new EmploymentDTO.Builder()
                        .employerINN("789456123456")
                        .salary(BigDecimal.valueOf(50000))
                        .position(Position.WORKER)
                        .withEmploymentStatus(EmploymentStatus.EMPLOYED)
                        .workExperienceTotal(60)
                        .workExperienceCurrent(36)
                        .build(),
                "account");
        CalculateController calculateController = mock(CalculateController.class);
        doNothing().when(calculateController).updateApplication(applicationId, data);

        mockMvc.perform(put("/deal/calculate/{applicationId}", applicationId)
                .content(objectMapper.writeValueAsString(data))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}