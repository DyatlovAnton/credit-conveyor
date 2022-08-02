package ru.neoflex.deal.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.neoflex.deal.embeddables.LoanOfferDTO;
import ru.neoflex.deal.embeddables.Passport;
import ru.neoflex.deal.models.Application;
import ru.neoflex.deal.models.Client;
import ru.neoflex.deal.repositories.ApplicationRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OfferServiceTest {
    @Mock
    ApplicationRepository applicationRepository;
    @Mock
    SendService sendService;
    @Test
    void updateApplication() {
        OfferService service = new OfferService(applicationRepository,sendService);
        LoanOfferDTO data = new LoanOfferDTO(1L, BigDecimal.valueOf(100000L), BigDecimal.valueOf(113668L), 36, BigDecimal.valueOf(3157.44), BigDecimal.valueOf(0.085), true, true);
        Long applicationId = 1L;

        Client client = new Client.Builder()
                .firstName("firstName")
                .lastName("lastName")
                .middleName("middleName")
                .birthDate(LocalDate.of(1970,1,1))
                .email("user@neoflex.ru")
                .passport(new Passport.Builder()
                        .series("1234")
                        .number("123456")
                        .build())
                .build();
        Application application = new Application.Builder()
                .client(client)
                .creationDate(LocalDate.now())
                .appliedOffer(new LoanOfferDTO(1L, BigDecimal.valueOf(100000L), BigDecimal.valueOf(113668L), 36, BigDecimal.valueOf(3157.44), BigDecimal.valueOf(0.085), true, true))
                .build();
        //applicationRepository.findById(data.getApplicationId())
        Mockito.when(applicationRepository.findById(applicationId)).thenReturn(Optional.of(application));

        service.updateApplication(data);
    }
}