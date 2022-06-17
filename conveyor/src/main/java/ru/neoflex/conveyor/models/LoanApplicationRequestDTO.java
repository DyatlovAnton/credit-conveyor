package ru.neoflex.conveyor.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;

@Getter
public class LoanApplicationRequestDTO {
    private final BigDecimal amount;
    private final Integer term;
    private final String firstName;
    private final String lastName;
    private final String middleName;
    private final String email;
    private final LocalDate birthdate;
    private final String passportSeries;
    private final String passportNumber;

    public LoanApplicationRequestDTO(BigDecimal amount, Integer term, String firstName, String lastName, String middleName, String email, LocalDate birthdate, String passportSeries, String passportNumber) {
        this.amount = amount;
        this.term = term;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.birthdate = birthdate;
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
    }

    @Override
    public String toString() {
        return "Amount: " + amount + ", Term: " + term + ", First Name: " + firstName + ", Last Name: " + lastName + ", Middle Name: " + middleName + ", Email: " + email + ", Birthdate: " + birthdate +
                ", Passport Series: " + passportSeries + ", Passport Number: " + passportNumber;
    }
}
