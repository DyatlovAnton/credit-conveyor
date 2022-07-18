package ru.neoflex.application.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.neoflex.application.models.LoanApplicationRequestDTO;
import ru.neoflex.application.models.LoanOfferDTO;

import java.util.List;

@FeignClient(value="feign", url="http://localhost:8081/deal")
public interface OffersClient {
    @RequestMapping(method = RequestMethod.POST, value = "/application", consumes = "application/json")
    List<LoanOfferDTO> getOffers(LoanApplicationRequestDTO data);

    @RequestMapping(method = RequestMethod.PUT, value = "/offer", consumes = "application/json")
    void sendOffer(LoanOfferDTO data);
}
