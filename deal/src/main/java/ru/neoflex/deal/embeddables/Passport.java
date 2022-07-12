package ru.neoflex.deal.embeddables;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Getter
@Embeddable
public class Passport {
    private String series;
    private String number;
    private LocalDate issueDate;
    private String issueBranch;
    public Passport(){};
    public Passport(String series, String number, LocalDate issueDate, String issueBranch){
        this.series = series;
        this.number = number;
        this.issueDate = issueDate;
        this.issueBranch = issueBranch;
    }
    public static class Builder{
        private String series = null;
        private String number = null;
        private LocalDate issueDate = null;
        private String issueBranch = null;
        public Builder(){};
        public Builder series(String series){
            this.series = series;
            return this;
        }
        public Builder number(String number){
            this.number = number;
            return this;
        }
        public Builder issueDate(LocalDate issueDate){
            this.issueDate = issueDate;
            return this;
        }
        public Builder issueBranch(String issueBranch){
            this.issueBranch = issueBranch;
            return this;
        }
        public Passport build(){
            return new Passport(this);
        }
    }
    private Passport(Builder builder){
        this.series = builder.series;
        this.number = builder.number;
        this.issueDate = builder.issueDate;
        this.issueBranch = builder.issueBranch;
    }
}
