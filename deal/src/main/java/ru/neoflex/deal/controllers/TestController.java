package ru.neoflex.deal.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import ru.neoflex.deal.models.Application;
import ru.neoflex.deal.models.Client;

import ru.neoflex.deal.repositories.ApplicationRepository;
import ru.neoflex.deal.repositories.ClientRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	private ApplicationRepository applicationRepository;
	private ClientRepository clientRepository;

	@Autowired
	public TestController(ApplicationRepository applicationRepository, ClientRepository clientRepository){
		this.applicationRepository = applicationRepository;
		this.clientRepository = clientRepository;
	}

   @GetMapping("/get")
	public Client get(@RequestParam(value = "id", defaultValue = "1") Long id) {
		Client client = clientRepository.findById(id).orElse(new Client(id));
		return client;
	}

	@GetMapping("/getapp")
	public Application getApp(@RequestParam(value = "id", defaultValue = "1") Long id) {
		Application application = applicationRepository.findById(id).orElse(new Application());
		return application;
	}


}
