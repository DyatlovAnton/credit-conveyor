package ru.neoflex.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.neoflex.gateway.models.database.Application;
import ru.neoflex.gateway.models.embeddables.LoanOfferDTO;
import ru.neoflex.gateway.models.input.FinishRegistrationRequestDTO;
import ru.neoflex.gateway.models.input.LoanApplicationRequestDTO;

import java.util.List;

@FeignClient(value = "feign1", url = "http://localhost:8081/deal")
public interface DealClient {
    @RequestMapping(method = RequestMethod.POST, value = "/document/{applicationId}/sign", consumes = "application/json")
    void sign(@PathVariable(value = "applicationId") Long applicationId);

    @RequestMapping(method = RequestMethod.GET, value = "/admin/application/{applicationId}")
    Application getApplicationById(@PathVariable(value = "applicationId") Long applicationId);

    @RequestMapping(method = RequestMethod.PUT, value = "/calculate/{applicationId}", consumes = "application/json")
    void calculate(@PathVariable(value = "applicationId") Long applicationId, @RequestBody FinishRegistrationRequestDTO data);

    @RequestMapping(method = RequestMethod.PUT, value = "/offer", consumes = "application/json")
    void updateApplication(@RequestBody LoanOfferDTO data);

    @RequestMapping(method = RequestMethod.GET, value = "/admin/application")
    List<Application> getAllApplications();

    @RequestMapping(method = RequestMethod.POST, value = "/document/{applicationId}/code", consumes = "application/json")
    void sendSES(@PathVariable Long applicationId);

    @RequestMapping(method = RequestMethod.POST, value = "/application", consumes = "application/json")
    List<LoanOfferDTO> getApplications(@RequestBody LoanApplicationRequestDTO data);

    @RequestMapping(method = RequestMethod.POST, value = "/document/{applicationId}/send", consumes = "application/json")
    void send(@PathVariable Long applicationId);
}
