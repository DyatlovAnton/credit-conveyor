package ru.neoflex.deal.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.neoflex.deal.embeddables.LoanOfferDTO;
import ru.neoflex.deal.input.LoanApplicationRequestDTO;
import ru.neoflex.deal.models.Credit;
import ru.neoflex.deal.models.ScoringDataDTO;

import java.util.List;

@FeignClient(value="feign", url="http://localhost:8080/conveyor")
public interface OffersClient {
    @RequestMapping(method = RequestMethod.POST, value = "/offers", consumes = "application/json")
    List<LoanOfferDTO> getOffers(LoanApplicationRequestDTO data);

    @RequestMapping(method = RequestMethod.POST, value = "/calculation", consumes = "application/json")
    Credit scoreCredit(ScoringDataDTO data);
}
