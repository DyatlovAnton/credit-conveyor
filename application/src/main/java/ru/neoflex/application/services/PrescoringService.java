package ru.neoflex.application.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Service;
import ru.neoflex.application.models.LoanApplicationRequestDTO;

@Service
public class PrescoringService {
    public boolean prescore(LoanApplicationRequestDTO data) {
        String name = data.getFirstName();
        String surname = data.getLastName();
        String patronymic = data.getMiddleName();
        BigDecimal amount = data.getAmount();
        Integer term = data.getTerm();
        LocalDate birthdate = data.getBirthdate();
        String email = data.getEmail();
        String passportSeries = data.getPassportSeries();
        String passportNumber = data.getPassportNumber();
        int age = Period.between(birthdate, LocalDate.now()).getYears();
        return ((name.length() >= 2 && name.length() <= 30) && (surname.length() >= 2 && surname.length() <= 30) && ((patronymic.length() >= 2 && patronymic.length() <= 30) || (patronymic.length() == 0))) &&
                (amount.compareTo(BigDecimal.valueOf(10000)) >= 0) && (term >= 6) && birthdate.toString().matches("[\\d]{4}-[\\d]{2}-[\\d]{2}") && (age >= 18) &&
                (email.matches("[\\w\\.]{2,50}@[\\w\\.]{2,20}")) && (passportSeries.matches("\\d{4}") && passportNumber.matches("\\d{6}"));
    }
}
