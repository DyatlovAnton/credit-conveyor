package ru.neoflex.deal.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

@Service
@Slf4j
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ClientRepository clientRepository;
    private final OffersClient offersClient;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository, ClientRepository clientRepository, OffersClient offersClient) {
        this.applicationRepository = applicationRepository;
        this.clientRepository = clientRepository;
        this.offersClient = offersClient;
    }

    public List<LoanOfferDTO> getApplication(LoanApplicationRequestDTO data) throws IllegalArgumentException, IllegalCallerException {
        Client client;
        try {
            client = new Client.Builder()
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
            log.info("API /deal/application: сущность Client - " + client);
            clientRepository.save(client);
            log.info("API /deal/application: сущность Client сохранена в базе данных");
        } catch (Exception e) {
            log.error("API /deal/application: Не верные входные данные");
            throw new IllegalArgumentException();
        }

        Application application = new Application.Builder()
                .client(client)
                .creationDate(LocalDate.now())
                .build();
        log.info("API /deal/application: сущность Application - " + application);
        applicationRepository.save(application);
        log.info("API /deal/application: сущность Application сохранена в базе данных");

        List<LoanOfferDTO> offers;
        try {
            offers = offersClient.getOffers(data);
            for (int i = 0; i < 4; i++) {
                offers.get(i).setApplicationId(application.getId());
                log.info("API /deal/application: " + i + "-е предложение - " + offers.get(i));
            }

        } catch (Exception e) {
            log.error("API /deal/application: Не получен ответ от mc conveyor");
            throw new IllegalCallerException();
        }
        return offers;
    }
}
