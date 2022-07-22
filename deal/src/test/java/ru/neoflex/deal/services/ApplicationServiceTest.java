package ru.neoflex.deal.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.neoflex.deal.embeddables.LoanOfferDTO;
import ru.neoflex.deal.feign.OffersClient;
import ru.neoflex.deal.input.LoanApplicationRequestDTO;
import ru.neoflex.deal.repositories.ApplicationRepository;
import ru.neoflex.deal.repositories.ClientRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceTest {

    @Mock
    ApplicationRepository applicationRepository;

    @Mock
    ClientRepository clientRepository;

    @Mock
    OffersClient offersClient;

    @Test
    void getApplication() {
        LoanApplicationRequestDTO input = new LoanApplicationRequestDTO(BigDecimal.valueOf(100000), 60, "firstName", "lastName", "middleName", "user@neoflex.ru", LocalDate.of(1970,1,1),"1234","123456");
        List<LoanOfferDTO> output = Arrays.asList(
                new LoanOfferDTO(1L, BigDecimal.valueOf(100000), BigDecimal.valueOf(132553.79), 60, BigDecimal.valueOf(2184.23), BigDecimal.valueOf(0.112), true, true),
                new LoanOfferDTO(1L, BigDecimal.valueOf(100000), BigDecimal.valueOf(135573.88), 60, BigDecimal.valueOf(2234.56), BigDecimal.valueOf(0.122), true, false),
                new LoanOfferDTO(1L, BigDecimal.valueOf(100000), BigDecimal.valueOf(140232.41), 60, BigDecimal.valueOf(2337.21), BigDecimal.valueOf(0.142), false, true),
                new LoanOfferDTO(1L, BigDecimal.valueOf(100000), BigDecimal.valueOf(143370.25), 60, BigDecimal.valueOf(2389.5), BigDecimal.valueOf(0.152), false, false)
        );
        ApplicationService service = new ApplicationService(applicationRepository, clientRepository, offersClient);
//        doNothing().when(clientRepository).save(isA(Client.class));
//        doNothing().when(applicationRepository).save(isA(Application.class));
        when(offersClient.getOffers(isA(LoanApplicationRequestDTO.class))).thenReturn(output);
        assertEquals(output,service.getApplication(input));
    }
}