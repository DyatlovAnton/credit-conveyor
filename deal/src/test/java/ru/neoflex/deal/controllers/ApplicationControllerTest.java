package ru.neoflex.deal.controllers;

import org.junit.jupiter.api.Test;
import ru.neoflex.deal.embeddables.LoanOfferDTO;
import ru.neoflex.deal.feign.OffersClient;
import ru.neoflex.deal.input.LoanApplicationRequestDTO;
import ru.neoflex.deal.repositories.ApplicationRepository;
import ru.neoflex.deal.repositories.ClientRepository;
import ru.neoflex.deal.services.ApplicationService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ApplicationControllerTest {
    private ApplicationRepository applicationRepository;
    private ClientRepository clientRepository;
    private OffersClient offersClient;
    LoanApplicationRequestDTO data=new LoanApplicationRequestDTO(BigDecimal.valueOf(300000L),36,"name","lastname",
            "middlename","user@neoflex.ru", LocalDate.of(1970,2,2),"4515","123456");
    private final ApplicationService applicationService=new ApplicationService(applicationRepository, clientRepository, offersClient);
    private final ApplicationController applicationController = new ApplicationController(applicationService);

    @Test
    void getApplicationTest(){
        List<LoanOfferDTO> a = applicationService.getApplication(data);
        for(LoanOfferDTO offer:a){
            System.out.println(offer);
        }
    }
}
