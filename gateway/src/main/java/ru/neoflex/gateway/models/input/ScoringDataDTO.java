package ru.neoflex.gateway.models.input;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.neoflex.gateway.models.embeddables.EmploymentDTO;
import ru.neoflex.gateway.models.enums.Gender;
import ru.neoflex.gateway.models.enums.MaritalStatus;

import lombok.Getter;

@Getter
@Schema(description = "Рассчетные данные")
public class ScoringDataDTO {
    @Schema(description = "Сумма")
    private final BigDecimal amount;
    @Schema(description = "Срок")
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
    @Schema(description = "Серия паспорта")
    private final String passportSeries;
    @Schema(description = "Номер паспорта")
    private final String passportNumber;
    @Schema(description = "Дата выдачи паспорта")
    private final LocalDate passportIssueDate;
    @Schema(description = "Орган выдавший паспорт")
    private final String passportIssueBranch;
    @Schema(description = "Семейное положение")
    private final MaritalStatus maritalStatus;
    @Schema(description = "Количество иждивенцев")
    private final Integer dependentAmount;
    @Schema(description = "Сотрудник")
    private final EmploymentDTO employment;
    @Schema(description = "Аккаунт")
    private final String account;
    @Schema(description = "Включена ли страховка")
    private final Boolean isInsuranceEnabled;
    @Schema(description = "Включен ли зарплатный клиент")
    private final Boolean isSalaryClient;

    @Override
    public String toString() {
        return "Amount: " + amount + ", Term: " + term + ", First name: " + firstName + ", Last name: " + lastName + ", Middle name: " + middleName + ", Gender: " + gender + ", Birthdate: " + birthdate +
                ", Passport series: " + passportSeries + ", Passport number: " + passportNumber + ", Passport issue date: " + passportIssueDate + ", Passport issue branch: " +
                passportIssueBranch + ", Marital status: " + maritalStatus + ", Dependent amount: " + dependentAmount + ", Employment: " + employment + ", Account: " + account +
                ", Is insurance enabled: " + isInsuranceEnabled + ", Is salary client: " + isSalaryClient;
    }

    public static class Builder {
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

        public Builder() {
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder term(Integer term) {
            this.term = term;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder middleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public Builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder birthdate(LocalDate birthdate) {
            this.birthdate = birthdate;
            return this;
        }

        public Builder passportSeries(String passportSeries) {
            this.passportSeries = passportSeries;
            return this;
        }

        public Builder passportNumber(String passportNumber) {
            this.passportNumber = passportNumber;
            return this;
        }

        public Builder passportIssueDate(LocalDate passportIssueDate) {
            this.passportIssueDate = passportIssueDate;
            return this;
        }

        public Builder passportIssueBranch(String passportIssueBranch) {
            this.passportIssueBranch = passportIssueBranch;
            return this;
        }

        public Builder maritalStatus(MaritalStatus maritalStatus) {
            this.maritalStatus = maritalStatus;
            return this;
        }

        public Builder dependentAmount(Integer dependentAmount) {
            this.dependentAmount = dependentAmount;
            return this;
        }

        public Builder employment(EmploymentDTO employment) {
            this.employment = employment;
            return this;
        }

        public Builder account(String account) {
            this.account = account;
            return this;
        }

        public Builder isInsuranceEnabled(Boolean isInsuranceEnabled) {
            this.isInsuranceEnabled = isInsuranceEnabled;
            return this;
        }

        public Builder isSalaryClient(Boolean isSalaryClient) {
            this.isSalaryClient = isSalaryClient;
            return this;
        }

        public ScoringDataDTO build() {
            return new ScoringDataDTO(this);
        }
    }

    public ScoringDataDTO(Builder builder) {
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