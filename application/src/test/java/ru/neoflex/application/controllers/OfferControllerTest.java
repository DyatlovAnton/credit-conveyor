package ru.neoflex.application.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.neoflex.application.feign.OffersClient;
import ru.neoflex.application.models.LoanOfferDTO;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class OfferControllerTest {

    @Mock
    OffersClient offersClient;

    LoanOfferDTO input = new LoanOfferDTO(0L, BigDecimal.valueOf(100000), BigDecimal.valueOf(119700.64), 36, BigDecimal.valueOf(3283.35), BigDecimal.valueOf(0.112), true, true);

    @Test
    void sendRequest() {
        OfferController controller = new OfferController(offersClient);
        controller.sendRequest(input);
    }

    @Test
    void sendRequest1() {
        doThrow(new NullPointerException()).when(offersClient).sendOffer(input);
        OfferController controller = new OfferController(offersClient);
        controller.sendRequest(input);
    }
}