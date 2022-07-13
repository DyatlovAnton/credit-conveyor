package ru.neoflex.deal.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import ru.neoflex.deal.models.Application;
import ru.neoflex.deal.models.Client;

import ru.neoflex.deal.models.CreditDTO;
import ru.neoflex.deal.repositories.ApplicationRepository;
import ru.neoflex.deal.repositories.ClientRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.deal.repositories.CreditRepository;

@RestController
public class TestController {
	private final ApplicationRepository applicationRepository;
	private final ClientRepository clientRepository;
	private final CreditRepository creditRepository;

	@Autowired
	public TestController(ApplicationRepository applicationRepository, ClientRepository clientRepository, CreditRepository creditRepository){
		this.applicationRepository = applicationRepository;
		this.clientRepository = clientRepository;
		this.creditRepository = creditRepository;
	}

   @GetMapping("/get_client")
	public Client get(@RequestParam(value = "id", defaultValue = "1") Long id) {
		Client client = clientRepository.findById(id).orElse(new Client(id));
		return client;
	}

	@GetMapping("/get_app")
	public Application getApp(@RequestParam(value = "id", defaultValue = "1") Long id) {
		Application application = applicationRepository.findById(id).orElse(new Application());
		return application;
	}
	@GetMapping("/get_credit")
	public CreditDTO getCredit(@RequestParam(value = "id", defaultValue = "1") Long id){
		CreditDTO credit = creditRepository.findById(id).orElse(new CreditDTO());
		return credit;
	}
}
