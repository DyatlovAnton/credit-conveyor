package ru.neoflex.deal.models;

import lombok.Getter;
import ru.neoflex.deal.embeddables.EmploymentDTO;
import ru.neoflex.deal.embeddables.Passport;
import ru.neoflex.deal.enums.Gender;
import ru.neoflex.deal.enums.MaritalStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Table(name="clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column
    private String email;

    @Column
    private Gender gender;

    @Column(name = "marital_status")
    private MaritalStatus maritalStatus;

    @Column(name = "dependent_amount")
    private Integer dependentAmount;

    @Column
    @Embedded
    private Passport passport;

    @Column
    @Embedded
    private EmploymentDTO employment;

    @Column
    private String account;

    public Client(){}
    public static class Builder{
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

        public Builder(){}

        public Builder lastName(String lastName){
            this.lastName = lastName;
            return this;
        }
        public Builder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }
        public Builder middleName(String middleName){
            this.middleName = middleName;
            return this;
        }
        public Builder birthDate(LocalDate birthDate){
            this.birthDate = birthDate;
            return this;
        }
        public Builder email(String email){
            this.email = email;
            return this;
        }
        public Builder gender(Gender gender){
            this.gender = gender;
            return this;
        }
        public Builder maritalStatus(MaritalStatus maritalStatus){
            this.maritalStatus = maritalStatus;
            return this;
        }
        public Builder dependentAmount(Integer dependentAmount){
            this.dependentAmount = dependentAmount;
            return this;
        }
        public Builder passport(Passport passport){
            this.passport = passport;
            return this;
        }
        public Builder employment(EmploymentDTO employment){
            this.employment = employment;
            return this;
        }
        public Builder account(String account){
            this.account = account;
            return this;
        }
        public Client build(){
            return new Client(this);
        }
    }
    private Client(Builder builder){
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
}
