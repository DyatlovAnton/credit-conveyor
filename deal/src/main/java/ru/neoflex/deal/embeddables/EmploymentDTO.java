package ru.neoflex.deal.embeddables;

import lombok.Getter;
import ru.neoflex.deal.enums.EmploymentStatus;
import ru.neoflex.deal.enums.Position;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Getter
@Embeddable
public class EmploymentDTO {
    private EmploymentStatus employmentStatus;
    private String employerINN;
    private BigDecimal salary;
    private Position position;
    private Integer workExperienceTotal;
    private Integer workExperienceCurrent;
    public static class Builder{
        private EmploymentStatus employmentStatus = null;
        private String employer = null;
        private BigDecimal salary = null;
        private Position position = null;
        private Integer workExperienceTotal = null;
        private Integer workExperienceCurrent = null;
        public Builder(){};
        public Builder withEmploymentStatus(EmploymentStatus employmentStatus){
            this.employmentStatus = employmentStatus;
            return this;
        }
        public Builder employerINN(String employer){
            this.employer = employer;
            return this;
        }
        public Builder salary(BigDecimal salary){
            this.salary = salary;
            return this;
        }
        public Builder position(Position position){
            this.position = position;
            return this;
        }
        public Builder workExperienceTotal(Integer workExperienceTotal){
            this.workExperienceTotal = workExperienceTotal;
            return this;
        }
        public Builder workExperienceCurrent(Integer workExperienceCurrent){
            this.workExperienceCurrent = workExperienceCurrent;
            return this;
        }
        public EmploymentDTO build(){
            return new EmploymentDTO(this);
        }
    }
    public EmploymentDTO(){};
    private EmploymentDTO(Builder builder){
        this.employmentStatus = builder.employmentStatus;
        this.employerINN = builder.employer;
        this.salary = builder.salary;
        this.position = builder.position;
        this.workExperienceTotal = builder.workExperienceTotal;
        this.workExperienceCurrent = builder.workExperienceCurrent;
    }
}
