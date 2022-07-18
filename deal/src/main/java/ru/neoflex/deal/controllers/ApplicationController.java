package ru.neoflex.deal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.deal.embeddables.LoanOfferDTO;
import ru.neoflex.deal.embeddables.Passport;
import ru.neoflex.deal.feign.OffersClient;
import ru.neoflex.deal.input.LoanApplicationRequestDTO;
import ru.neoflex.deal.models.Application;
import ru.neoflex.deal.models.Client;
import ru.neoflex.deal.repositories.ApplicationRepository;
import ru.neoflex.deal.repositories.ClientRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ApplicationController {
    private final ApplicationRepository applicationRepository;
    private final ClientRepository clientRepository;
    private final OffersClient offersClient;

    @Autowired
    public ApplicationController(ApplicationRepository applicationRepository, ClientRepository clientRepository, OffersClient offersClient){
        this.applicationRepository = applicationRepository;
        this.clientRepository = clientRepository;
        this.offersClient = offersClient;
    }

    @PostMapping("/deal/application")
    public List<LoanOfferDTO> getApplication(@RequestBody LoanApplicationRequestDTO data){
        Client client = new Client.Builder()
                .firstName(data.getFirstName())
                .lastName(data.getLastName())
                .middleName(data.getMiddleName())
                .birthDate(data.getBirthdate())
                .email(data.getEmail())
                .passport(new Passport.Builder()
                        .series(data.getPassportSeries())
                        .number(data.getPassportNumber())
                        .build())
                .build();
        clientRepository.save(client);

        Application application = new Application.Builder()
                .client(client)
                .creationDate(LocalDate.now())
                .build();
        applicationRepository.save(application);

        List<LoanOfferDTO> offers = offersClient.getOffers(data);
        for (LoanOfferDTO offer : offers) {
            offer.setApplicationId(application.getId());
        }
        return offers;
    }
}
