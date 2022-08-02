package ru.neoflex.gateway.models.database;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import ru.neoflex.gateway.models.embeddables.ApplicationStatusHistoryDTO;
import ru.neoflex.gateway.models.embeddables.LoanOfferDTO;
import ru.neoflex.gateway.models.enums.ApplicationStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Schema(description = "Сущность Заявки на кредит")
public class Application {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "Клиент")
    private Client client;

    @Schema(description = "Кредит")
    private CreditDTO credit;

    @Schema(description = "Статус заявки")
    private ApplicationStatus applicationStatus;

    @Schema(description = "Дата создания")
    private LocalDate creationDate;

    @Schema(description = "Принятое предложение")
    private LoanOfferDTO appliedOffer;

    @Schema(description = "Дата подписания")
    private LocalDate signDate;

    @Schema(description = "SES код")
    private String sesCode;

    @Schema(description = "История статусов")
    private List<ApplicationStatusHistoryDTO> statusHistory = new ArrayList<>();

    public static class Builder {
        private final Long id = null;
        private Client client = null;
        private CreditDTO credit = null;
        private ApplicationStatus applicationStatus = null;
        private LocalDate creationDate = null;
        private LoanOfferDTO appliedOffer = null;
        private LocalDate signDate = null;
        private String sesCode = null;
        private List<ApplicationStatusHistoryDTO> statusHistory = new ArrayList<>();

        public Builder() {
        }

        public Builder client(Client client) {
            this.client = client;
            return this;
        }

        public Builder credit(CreditDTO credit) {
            this.credit = credit;
            return this;
        }

        public Builder applicationStatus(ApplicationStatus applicationStatus) {
            this.applicationStatus = applicationStatus;
            return this;
        }

        public Builder creationDate(LocalDate creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public Builder appliedOffer(LoanOfferDTO appliedOffer) {
            this.appliedOffer = appliedOffer;
            return this;
        }

        public Builder signDate(LocalDate signDate) {
            this.signDate = signDate;
            return this;
        }

        public Builder sesCode(String sesCode) {
            this.sesCode = sesCode;
            return this;
        }

        public Builder statusHistory(List<ApplicationStatusHistoryDTO> statusHistory) {
            this.statusHistory = statusHistory;
            return this;
        }

        public Application build() {
            return new Application(this);
        }
    }

    public Application() {
    }

    private Application(Builder builder) {
        this.client = builder.client;
        this.credit = builder.credit;
        this.applicationStatus = builder.applicationStatus;
        this.creationDate = builder.creationDate;
        this.appliedOffer = builder.appliedOffer;
        this.signDate = builder.signDate;
        this.sesCode = builder.sesCode;
        this.statusHistory = builder.statusHistory;
    }

    @Override
    public String toString() {
        String startString = "ID: " + id + ", Client: " + client + ", Credir: " + credit + ", Application status: " + applicationStatus + ", Creation date: " +
                creationDate + ", Applied offer: " + appliedOffer + ", Sign date: " + signDate + ", SES code: " + sesCode + ", Status history: { ";
        String endString = " }";
        StringBuilder sb = new StringBuilder();
        for (ApplicationStatusHistoryDTO applicationStatusHistoryDTO : statusHistory) {
            sb.append(applicationStatusHistoryDTO).append(";");
        }
        return startString + sb + endString;
    }
}
