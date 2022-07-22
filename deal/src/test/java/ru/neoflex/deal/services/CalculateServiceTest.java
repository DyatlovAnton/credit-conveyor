package ru.neoflex.deal.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.neoflex.deal.embeddables.EmploymentDTO;
import ru.neoflex.deal.enums.EmploymentStatus;
import ru.neoflex.deal.enums.Gender;
import ru.neoflex.deal.enums.MaritalStatus;
import ru.neoflex.deal.enums.Position;
import ru.neoflex.deal.feign.OffersClient;
import ru.neoflex.deal.models.Application;
import ru.neoflex.deal.models.FinishRegistrationRequestDTO;
import ru.neoflex.deal.models.ScoringDataDTO;
import ru.neoflex.deal.repositories.ApplicationRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
class CalculateServiceTest {

    @Mock
    ApplicationRepository applicationRepository;
    @Mock
    OffersClient offersClient;

    @Test
    void updateApplication() {
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
        CalculateService service=new CalculateService(applicationRepository,offersClient);
        Mockito.when(applicationRepository.findById(applicationId)).thenReturn(Optional.of(new Application()));

        assertThrows(NullPointerException.class, ()->{service.updateApplication(applicationId,data);});
    }
}