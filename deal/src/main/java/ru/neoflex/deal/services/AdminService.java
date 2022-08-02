package ru.neoflex.deal.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neoflex.deal.models.Application;
import ru.neoflex.deal.repositories.ApplicationRepository;

import java.util.List;

@Service
@Slf4j
public class AdminService {
    private final ApplicationRepository applicationRepository;

    @Autowired
    public AdminService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }
    public Application getApplicationById(long applicationId) throws Exception{
        return applicationRepository.findById(applicationId).orElseThrow(Exception::new);
    }
    public List<Application> getAllApplications(){
        return applicationRepository.findAll();
    }
}
