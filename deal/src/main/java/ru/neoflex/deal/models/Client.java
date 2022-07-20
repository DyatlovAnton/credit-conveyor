package ru.neoflex.deal.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import ru.neoflex.deal.embeddables.EmploymentDTO;
import ru.neoflex.deal.embeddables.Passport;
import ru.neoflex.deal.enums.Gender;
import ru.neoflex.deal.enums.MaritalStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "clients")
@Schema(description = "Сущность клиента")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "ID")
    private Long id;

    @Column(name = "last_name")
    @Schema(description = "Фамилия")
    private String lastName;

    @Column(name = "first_name")
    @Schema(description = "Имя")
    private String firstName;

    @Column(name = "middle_name")
    @Schema(description = "Отчество")
    private String middleName;

    @Column(name = "birth_date")
    @Schema(description = "Дата рождения")
    private LocalDate birthDate;

    @Column
    @Schema(description = "Электронная почта")
    private String email;

    @Column
    @Schema(description = "Пол")
    private Gender gender;

    @Column(name = "marital_status")
    @Schema(description = "Семейное положение")
    private MaritalStatus maritalStatus;

    @Column(name = "dependent_amount")
    @Schema(description = "Количество иждивенцев")
    private Integer dependentAmount;

    @Column
    @Embedded
    @Schema(description = "Пасспорт")
    private Passport passport;

    @Column
    @Embedded
    @Schema(description = "Сущность сотрудника")
    private EmploymentDTO employment;

    @Column
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
