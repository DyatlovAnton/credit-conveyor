package ru.neoflex.gateway.models.database;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import ru.neoflex.gateway.models.embeddables.EmploymentDTO;
import ru.neoflex.gateway.models.embeddables.Passport;
import ru.neoflex.gateway.models.enums.Gender;
import ru.neoflex.gateway.models.enums.MaritalStatus;

import java.time.LocalDate;

@Getter
@Schema(description = "Сущность клиента")
public class Client {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "Фамилия")
    private String lastName;

    @Schema(description = "Имя")
    private String firstName;

    @Schema(description = "Отчество")
    private String middleName;

    @Schema(description = "Дата рождения")
    private LocalDate birthDate;

    @Schema(description = "Электронная почта")
    private String email;

    @Schema(description = "Пол")
    private Gender gender;

    @Schema(description = "Семейное положение")
    private MaritalStatus maritalStatus;

    @Schema(description = "Количество иждивенцев")
    private Integer dependentAmount;

    @Schema(description = "Пасспорт")
    private Passport passport;

    @Schema(description = "Сущность сотрудника")
    private EmploymentDTO employment;

    @Schema(description = "Аккаунт")
    private String account;

    public Client() {
    }

    public static class Builder {
        private String lastName = null;
        private String firstName = null;
        private String middleName = null;
        private LocalDate birthDate = null;
        private String email = null;
        private Gender gender = null;
        private MaritalStatus maritalStatus = null;
        private Integer dependentAmount = null;
        private Passport passport = null;
        private EmploymentDTO employment = null;
        private String account = null;

        public Builder() {
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder middleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public Builder birthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder gender(Gender gender) {
            this.gender = gender;
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

        public Builder passport(Passport passport) {
            this.passport = passport;
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

        public Client build() {
            return new Client(this);
        }
    }

    private Client(Builder builder) {
        this.lastName = builder.lastName;
        this.firstName = builder.firstName;
        this.middleName = builder.middleName;
        this.birthDate = builder.birthDate;
        this.email = builder.email;
        this.gender = builder.gender;
        this.maritalStatus = builder.maritalStatus;
        this.dependentAmount = builder.dependentAmount;
        this.passport = builder.passport;
        this.employment = builder.employment;
        this.account = builder.account;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Last name: " + lastName + ", First name: " + firstName + ", Middle name: " + middleName + ", birthDate: " + birthDate +
                ", Email: " + email + ", Gender: " + gender + ", Marital status: " + maritalStatus + ", Dependent amount: " + dependentAmount +
                ", Passport: " + passport + ", Employment: " + employment + ", Account: " + account;
    }
}
