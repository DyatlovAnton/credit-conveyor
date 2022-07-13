package ru.neoflex.deal.models;

import lombok.Getter;
import lombok.Setter;
import ru.neoflex.deal.embeddables.AdditionalServices;
import ru.neoflex.deal.embeddables.PaymentScheduleElement;
import ru.neoflex.deal.enums.CreditStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "credits")
public class CreditDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private BigDecimal amount;

    @Column
    private Integer term;

    @Column(name = "monthly_payment")
    private BigDecimal monthlyPayment;

    @Column
    private BigDecimal rate;

    @Column
    private BigDecimal psk;

    @Column(name = "payment_schedule")
    @ElementCollection
    private List<PaymentScheduleElement> paymentSchedule;

    @Column(name = "additional_service")
    @Embedded
    private AdditionalServices additionalServices;

    @Column(name = "credit_status")
    private CreditStatus creditStatus;

    public static class Builder{
        private BigDecimal amount = null;
        private Integer term = null;
        private BigDecimal monthlyPayment = null;
        private BigDecimal rate = null;
        private BigDecimal psk = null;
        private List<PaymentScheduleElement> paymentSchedule = new ArrayList<>();
        private AdditionalServices additionalServices = null;
        private CreditStatus creditStatus = null;

        public Builder(){};

        public Builder amount(BigDecimal amount){
            this.amount = amount;
            return this;
        }
        public Builder term(Integer term){
            this.term = term;
            return this;
        }
        public Builder monthlyPayment(BigDecimal monthlyPayment){
            this.monthlyPayment = monthlyPayment;
            return this;
        }
        public Builder rate(BigDecimal rate){
            this.rate = rate;
            return this;
        }
        public Builder psk(BigDecimal psk){
            this.psk = psk;
            return this;
        }
        public Builder paymentSchedule(List<PaymentScheduleElement> paymentSchedule){
            this.paymentSchedule = paymentSchedule;
            return this;
        }
        public Builder additionalServices(AdditionalServices additionalServices){
            this.additionalServices = additionalServices;
            return this;
        }
        public Builder creditStatus(CreditStatus creditStatus){
            this.creditStatus = creditStatus;
            return this;
        }
        public CreditDTO build(){
            return new CreditDTO(this);
        }
    }
    public CreditDTO(){}
    private CreditDTO(Builder builder){
        this.amount = builder.amount;
        this.term = builder.term;
        this.monthlyPayment = builder.monthlyPayment;
        this.rate = builder.rate;
        this.psk = builder.psk;
        this.paymentSchedule = builder.paymentSchedule;
        this.additionalServices = builder.additionalServices;
        this.creditStatus = builder.creditStatus;
    }
}
