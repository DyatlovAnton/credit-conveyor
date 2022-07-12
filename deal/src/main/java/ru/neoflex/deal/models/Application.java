package ru.neoflex.deal.models;

import lombok.Getter;
import lombok.Setter;
import ru.neoflex.deal.embeddables.ApplicationStatusHistoryDTO;
import ru.neoflex.deal.embeddables.LoanOfferDTO;
import ru.neoflex.deal.enums.ApplicationStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @OneToOne
    @JoinColumn(name = "credit_id", referencedColumnName = "id")
    private Credit credit;

    @Column(name="application_status")
    private ApplicationStatus applicationStatus;

    @Column(name="creation_date")
    private LocalDate creationDate;

    @Column(name="applied_offer")
    @Embedded
    private LoanOfferDTO appliedOffer;

    @Column(name="sign_date")
    private LocalDate signDate;

    @Column(name="ses_code")
    private String sesCode;

    @Column(name="status_history")
    @ElementCollection
    private List<ApplicationStatusHistoryDTO> statusHistory = new ArrayList<>();

    public static class Builder{
        private Long id = null;
        private Client client = null;
        private Credit credit = null;
        private ApplicationStatus applicationStatus = null;
        private LocalDate creationDate = null;
        private LoanOfferDTO appliedOffer = null;
        private LocalDate signDate = null;
        private String sesCode = null;
        private List<ApplicationStatusHistoryDTO> statusHistory = new ArrayList<>();

        public Builder(){}
        public Builder client(Client client){
            this.client = client;
            return this;
        }
        public Builder credit(Credit credit){
            this.credit = credit;
            return this;
        }
        public Builder applicationStatus(ApplicationStatus applicationStatus){
            this.applicationStatus = applicationStatus;
            return this;
        }
        public Builder creationDate(LocalDate creationDate){
            this.creationDate = creationDate;
            return this;
        }
        public Builder appliedOffer(LoanOfferDTO appliedOffer){
            this.appliedOffer = appliedOffer;
            return this;
        }
        public Builder signDate(LocalDate signDate){
            this.signDate = signDate;
            return this;
        }
        public Builder sesCode(String sesCode){
            this.sesCode = sesCode;
            return this;
        }
        public Builder statusHistory(List<ApplicationStatusHistoryDTO> statusHistory){
            this.statusHistory = statusHistory;
            return this;
        }
        public Application build(){
            return new Application(this);
        }
    }
    public Application(){}
    private  Application(Builder builder){
        this.client = builder.client;
        this.credit = builder.credit;
        this.applicationStatus = builder.applicationStatus;
        this.creationDate = builder.creationDate;
        this.appliedOffer = builder.appliedOffer;
        this.signDate = builder.signDate;
        this.sesCode = builder.sesCode;
        this.statusHistory = builder.statusHistory;
    }
}
