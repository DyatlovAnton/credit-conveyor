package ru.neoflex.gateway.models.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import ru.neoflex.gateway.models.embeddables.EmploymentDTO;
import ru.neoflex.gateway.models.enums.Gender;
import ru.neoflex.gateway.models.enums.MaritalStatus;
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

    public FinishRegistrationRequestDTO(Gender gender, MaritalStatus maritalStatus, Integer dependentAmount, LocalDate passportIssueDate, String passportIssueBranch, EmploymentDTO employment, String account) {
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.dependentAmount = dependentAmount;
        this.passportIssueDate = passportIssueDate;
        this.passportIssueBranch = passportIssueBranch;
        this.employment = employment;
        this.account = account;
    }

    @Override
    public String toString() {
        return "Gender: " + gender + ", Marital status: " + maritalStatus + ", Dependent amount: " + dependentAmount + ", Passport issue date: " +
                passportIssueDate + ", Passport issue branch: " + passportIssueBranch + ", Employment: " + employment + ", Account: " + account;
    }
}
