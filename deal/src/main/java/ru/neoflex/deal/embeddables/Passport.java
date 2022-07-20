package ru.neoflex.deal.embeddables;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Getter
@Embeddable
@Schema(description = "Паспорт")
public class Passport {
    @Schema(description = "Серия")
    private String series;
    @Schema(description = "Номер")
    private String number;
    @Schema(description = "Дата выдачи")
    private LocalDate issueDate;
    @Schema(description = "Орган выдавший паспорт")
    private String issueBranch;

    public Passport() {
    }

    ;

    public static class Builder {
        private String series = null;
        private String number = null;
        private LocalDate issueDate = null;
        private String issueBranch = null;

        public Builder() {
        }

        public Builder series(String series) {
            this.series = series;
            return this;
        }

        public Builder number(String number) {
            this.number = number;
            return this;
        }

        public Builder issueDate(LocalDate issueDate) {
            this.issueDate = issueDate;
            return this;
        }

        public Builder issueBranch(String issueBranch) {
            this.issueBranch = issueBranch;
            return this;
        }

        public Passport build() {
            return new Passport(this);
        }
    }

    private Passport(Builder builder) {
        this.series = builder.series;
        this.number = builder.number;
        this.issueDate = builder.issueDate;
        this.issueBranch = builder.issueBranch;
    }
}
