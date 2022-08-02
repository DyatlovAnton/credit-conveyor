package ru.neoflex.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.neoflex.gateway.models.embeddables.LoanOfferDTO;
import ru.neoflex.gateway.models.input.LoanApplicationRequestDTO;

import java.util.List;

@FeignClient(value = "feign2", url = "${application.url}")
public interface ApplicationClient {
    @RequestMapping(method = RequestMethod.POST, value = "/application", consumes = "application/json")
    List<LoanOfferDTO> getLoanOfferDTO(@RequestBody LoanApplicationRequestDTO data);

    @RequestMapping(method = RequestMethod.PUT, value = "/application/offer", consumes = "application/json")
    void sendRequest(@RequestBody LoanOfferDTO data);
}
