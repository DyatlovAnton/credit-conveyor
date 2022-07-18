package ru.neoflex.conveyor.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "Сущность заявки на кредит")
public class LoanApplicationRequestDTO {
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
    @Schema(description = "Электронная почта")
    private final String email;
    @Schema(description = "Дата рождения")
    private final LocalDate birthdate;
    @Schema(description = "Серия пасспорта")
    private final String passportSeries;
    @Schema(description = "Номер пасспорта")
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
