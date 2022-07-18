package ru.neoflex.deal.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import ru.neoflex.deal.enums.MaritalStatus;
import ru.neoflex.deal.enums.Gender;
import ru.neoflex.deal.embeddables.EmploymentDTO;

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

    @Override
    public String toString() {
        return "Amount: " + amount + ", Term: " + term + ", First name: " + firstName + ", Last name: " + lastName + ", Middle name: " + middleName + ", Gender: " + gender + ", Birthdate: " + birthdate +
                ", Passport series: " + passportSeries + ", Passport number: " + passportNumber + ", Passport issue date: " + passportIssueDate + ", Passport issue branch: " +
                passportIssueBranch + ", Marital status: " + maritalStatus + ", Dependent amount: " + dependentAmount + ", Employment: " + employment + ", Account: " + account +
                ", Is insurance enabled: " + isInsuranceEnabled + ", Is salary client: " + isSalaryClient;
    }
    public static class Builder{
        private BigDecimal amount = null;
        private Integer term = null;
        private String firstName = null;
        private String lastName = null;
        private String middleName = null;
        private Gender gender = null;
        private LocalDate birthdate = null;
        private String passportSeries = null;
        private String passportNumber = null;
        private LocalDate passportIssueDate = null;
        private String passportIssueBranch = null;
        private MaritalStatus maritalStatus = null;
        private Integer dependentAmount = null;
        private EmploymentDTO employment = null;
        private String account = null;
        private Boolean isInsuranceEnabled = null;
        private Boolean isSalaryClient = null;

        public Builder(){}

        public Builder amount(BigDecimal amount){
            this.amount = amount;
            return this;
        }
        public Builder term(Integer term){
            this.term = term;
            return this;
        }
        public Builder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }
        public Builder lastName(String lastName){
            this.lastName = lastName;
            return this;
        }
        public Builder middleName(String middleName){
            this.middleName = middleName;
            return this;
        }
        public Builder gender(Gender gender){
            this.gender = gender;
            return this;
        }
        public Builder birthdate(LocalDate birthdate){
            this.birthdate = birthdate;
            return this;
        }
        public Builder passportSeries(String passportSeries){
            this.passportSeries = passportSeries;
            return this;
        }
        public Builder passportNumber(String passportNumber){
            this.passportNumber = passportNumber;
            return this;
        }
        public Builder passportIssueDate(LocalDate passportIssueDate){
            this.passportIssueDate = passportIssueDate;
            return this;
        }
        public Builder passportIssueBranch(String passportIssueBranch){
            this.passportIssueBranch = passportIssueBranch;
            return this;
        }
        public Builder maritalStatus(MaritalStatus maritalStatus){
            this.maritalStatus = maritalStatus;
            return this;
        }
        public Builder dependentAmount(Integer dependentAmount){
            this.dependentAmount = dependentAmount;
            return this;
        }
        public Builder employment(EmploymentDTO employment){
            this.employment = employment;
            return this;
        }
        public Builder account(String account){
            this.account = account;
            return this;
        }
        public Builder isInsuranceEnabled(Boolean isInsuranceEnabled){
            this.isInsuranceEnabled = isInsuranceEnabled;
            return  this;
        }
        public Builder isSalaryClient(Boolean isSalaryClient){
            this.isSalaryClient = isSalaryClient;
            return this;
        }
        public ScoringDataDTO build(){
            return new ScoringDataDTO(this);
        }
    }
    public ScoringDataDTO(Builder builder){
        this.amount = builder.amount;
        this.term = builder.term;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.middleName = builder.middleName;
        this.gender = builder.gender;
        this.birthdate = builder.birthdate;
        this.passportSeries = builder.passportSeries;
        this.passportNumber = builder.passportNumber;
        this.passportIssueDate = builder.passportIssueDate;
        this.passportIssueBranch = builder.passportIssueBranch;
        this.maritalStatus = builder.maritalStatus;
        this.dependentAmount = builder.dependentAmount;
        this.employment = builder.employment;
        this.account = builder.account;
        this.isInsuranceEnabled = builder.isInsuranceEnabled;
        this.isSalaryClient = builder.isSalaryClient;
    }
}
