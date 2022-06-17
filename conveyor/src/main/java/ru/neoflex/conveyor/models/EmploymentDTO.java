package ru.neoflex.conveyor.models;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class EmploymentDTO {
    private final EmploymentStatus employmentStatus;
    private final String employerINN;
    private final BigDecimal salary;
    private final Position position;
    private final Integer workExperienceTotal;
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
