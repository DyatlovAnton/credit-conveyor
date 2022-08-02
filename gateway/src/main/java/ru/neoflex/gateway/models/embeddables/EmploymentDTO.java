package ru.neoflex.gateway.models.embeddables;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import ru.neoflex.gateway.models.enums.EmploymentStatus;
import ru.neoflex.gateway.models.enums.Position;

import java.math.BigDecimal;

@Getter
@Schema(description = "Сущность сотрудника")
public class EmploymentDTO {
    @Schema(description = "Статус сотрудника")
    private EmploymentStatus employmentStatus;
    @Schema(description = "ИНН сотрудника")
    private String employerINN;
    @Schema(description = "Зарплата")
    private BigDecimal salary;
    @Schema(description = "Позиция")
    private Position position;
    @Schema(description = "Общий опыт работы")
    private Integer workExperienceTotal;
    @Schema(description = "Текущий опыт работы")
    private Integer workExperienceCurrent;

    public EmploymentDTO() {
    }

    public static class Builder {
        private EmploymentStatus employmentStatus = null;
        private String employer = null;
        private BigDecimal salary = null;
        private Position position = null;
        private Integer workExperienceTotal = null;
        private Integer workExperienceCurrent = null;

        public Builder() {
        }

        public Builder withEmploymentStatus(EmploymentStatus employmentStatus) {
            this.employmentStatus = employmentStatus;
            return this;
        }

        public Builder employerINN(String employer) {
            this.employer = employer;
            return this;
        }

        public Builder salary(BigDecimal salary) {
            this.salary = salary;
            return this;
        }

        public Builder position(Position position) {
            this.position = position;
            return this;
        }

        public Builder workExperienceTotal(Integer workExperienceTotal) {
            this.workExperienceTotal = workExperienceTotal;
            return this;
        }

        public Builder workExperienceCurrent(Integer workExperienceCurrent) {
            this.workExperienceCurrent = workExperienceCurrent;
            return this;
        }

        public EmploymentDTO build() {
            return new EmploymentDTO(this);
        }
    }

    private EmploymentDTO(Builder builder) {
        this.employmentStatus = builder.employmentStatus;
        this.employerINN = builder.employer;
        this.salary = builder.salary;
        this.position = builder.position;
        this.workExperienceTotal = builder.workExperienceTotal;
        this.workExperienceCurrent = builder.workExperienceCurrent;
    }
}
