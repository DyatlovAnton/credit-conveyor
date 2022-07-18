package ru.neoflex.conveyor.models;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "Сущность сотрудника")
public class EmploymentDTO {
    @Schema(description = "Статус работника")
    private final EmploymentStatus employmentStatus;
    @Schema(description = "ИНН сотрудника")
    private final String employerINN;
    @Schema(description = "Зарплата")
    private final BigDecimal salary;
    @Schema(description = "Позиция сотрудника")
    private final Position position;
    @Schema(description = "Общий трудовой стаж")
    private final Integer workExperienceTotal;
    @Schema(description = "Текущий трудовой стаж")
    private final Integer workExperienceCurrent;

    public EmploymentDTO(EmploymentStatus employmentStatus, String employerINN, BigDecimal salary, Position position, Integer workExperienceTotal, Integer workExperienceCurrent) {
        this.employmentStatus = employmentStatus;
        this.employerINN = employerINN;
        this.salary = salary;
        this.position = position;
        this.workExperienceTotal = workExperienceTotal;
        this.workExperienceCurrent = workExperienceCurrent;
    }
}
