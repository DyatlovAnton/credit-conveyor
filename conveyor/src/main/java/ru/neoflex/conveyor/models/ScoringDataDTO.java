package ru.neoflex.conveyor.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;

@Getter
public class ScoringDataDTO {
    private final BigDecimal amount;
    private final Integer term;
    private final String firstName;
    private final String lastName;
    private final String middleName;
    private final Gender gender;
    private final LocalDate birthdate;
    private final String passportSeries;
    private final String passportNumber;
    private final LocalDate passportIssueDate;
    private final String passportIssueBranch;
    private final MaritalStatus maritalStatus;
    private final Integer dependentAmount;
    private final EmploymentDTO employment;
    private final String account;
    private final Boolean isInsuranceEnabled;
    private final Boolean isSalaryClient;

    public ScoringDataDTO(BigDecimal amount, Integer term, String firstName, String lastName, String middleName, Gender gender, LocalDate birthdate, String passportSeries, String passportNumber, LocalDate passportIssueDate, String passportIssueBranch, MaritalStatus maritalStatus, Integer dependentAmount, EmploymentDTO employment, String account, Boolean isInsuranceEnabled, Boolean isSalaryClient) {
        this.amount = amount;
        this.term = term;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.gender = gender;
        this.birthdate = birthdate;
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        this.passportIssueDate = passportIssueDate;
        this.passportIssueBranch = passportIssueBranch;
        this.maritalStatus = maritalStatus;
        this.dependentAmount = dependentAmount;
        this.employment = employment;
        this.account = account;
        this.isInsuranceEnabled = isInsuranceEnabled;
        this.isSalaryClient = isSalaryClient;
    }

    @Override
    public String toString() {
        return "Amount: " + amount + ", Term: " + term + "First name: " + firstName + ", Last name: " + lastName + ", Middle name: " + middleName + ", Gender: " + gender + ", Birthdate: " + birthdate +
                ", Passport series: " + passportSeries + ", Passport number: " + passportNumber + ", Passport issue date: " + passportIssueDate + ", Passport issue branch: " +
                passportIssueBranch + ", Marital status: " + maritalStatus + ", Dependent amount: " + dependentAmount + ", Employment: " + employment + ", Account: " + account +
                ", Is insurance enabled: " + isInsuranceEnabled + ", Is salary client: " + isSalaryClient;
    }
}
