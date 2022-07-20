package ru.neoflex.deal.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import ru.neoflex.deal.embeddables.EmploymentDTO;
import ru.neoflex.deal.enums.Gender;
import ru.neoflex.deal.enums.MaritalStatus;

import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "Запрос завершения регистрации")
public class FinishRegistrationRequestDTO {
    @Schema(description = "Пол")
    private Gender gender;
    @Schema(description = "Семейное положение")
    private MaritalStatus maritalStatus;
    @Schema(description = "Число иждивенцев")
    private Integer dependentAmount;
    @Schema(description = "Дата выдачи паспорта")
    private LocalDate passportIssueDate;
    @Schema(description = "Орган выдавший паспорт")
    private String passportIssueBranch;
    @Schema(description = "Сотрудник")
    private EmploymentDTO employment;
    @Schema(description = "Аккаунт")
    private String account;

    @Override
    public String toString() {
        return "Gender: " + gender + ", Marital status: " + maritalStatus + ", Dependent amount: " + dependentAmount + ", Passport issue date: " +
                passportIssueDate + ", Passport issue branch: " + passportIssueBranch + ", Employment: " + employment + ", Account: " + account;
    }
}
