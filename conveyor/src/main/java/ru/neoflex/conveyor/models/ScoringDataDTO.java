package ru.neoflex.conveyor.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "Сущность расчетных данных")
public class ScoringDataDTO {
    @Schema(description = "Сумма кредита")
    private final BigDecimal amount;
    @Schema(description = "Срок кредита")
    private final Integer term;
    @Schema(description = "Имя")
    private final String firstName;
    @Schema(description = "Фамилия")
    private final String lastName;
    @Schema(description = "Отчество")
    private final String middleName;
    @Schema(description = "Пол")
    private final Gender gender;
    @Schema(description = "Дата рождения")
    private final LocalDate birthdate;
    @Schema(description = "Серия пасспорта")
    private final String passportSeries;
    @Schema(description = "Номер паспорта")
    private final String passportNumber;
    @Schema(description = "Дата выдачи паспорта")
    private final LocalDate passportIssueDate;
    @Schema(description = "Отдел выдавший паспорт")
    private final String passportIssueBranch;
    @Schema(description = "Семейное положение")
    private final MaritalStatus maritalStatus;
    @Schema(description = "Количество иждивенцев")
    private final Integer dependentAmount;
    @Schema(description = "Сущность сотрудника")
    private final EmploymentDTO employment;
    @Schema(description = "Учетная запись")
    private final String account;
    @Schema(description = "Включена ли страховка")
    private final Boolean isInsuranceEnabled;
    @Schema(description = "Включен ли зарплатный клиент")
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
