package ru.neoflex.gateway.models.database;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import ru.neoflex.gateway.models.embeddables.AdditionalServices;
import ru.neoflex.gateway.models.embeddables.PaymentScheduleElement;
import ru.neoflex.gateway.models.enums.CreditStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Schema(description = "Сущность кредита")
public class CreditDTO {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "Сумма")
    private BigDecimal amount;

    @Schema(description = "Срок")
    private Integer term;

    @Schema(description = "Ежемесячный платеж")
    private BigDecimal monthlyPayment;

    @Schema(description = "Процентная ставка")
    private BigDecimal rate;

    @Schema(description = "Полная стоимость кредита")
    private BigDecimal psk;

    @Schema(description = "График платежей")
    private List<PaymentScheduleElement> paymentSchedule;

    @Schema(description = "Дополнительные услуги")
    private AdditionalServices additionalServices;

    @Schema(description = "Статус кредита")
    private CreditStatus creditStatus;

    public static class Builder {
        private BigDecimal amount = null;
        private Integer term = null;
        private BigDecimal monthlyPayment = null;
        private BigDecimal rate = null;
        private BigDecimal psk = null;
        private List<PaymentScheduleElement> paymentSchedule = new ArrayList<>();
        private AdditionalServices additionalServices = null;
        private CreditStatus creditStatus = null;

        public Builder() {
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder term(Integer term) {
            this.term = term;
            return this;
        }

        public Builder monthlyPayment(BigDecimal monthlyPayment) {
            this.monthlyPayment = monthlyPayment;
            return this;
        }

        public Builder rate(BigDecimal rate) {
            this.rate = rate;
            return this;
        }

        public Builder psk(BigDecimal psk) {
            this.psk = psk;
            return this;
        }

        public Builder paymentSchedule(List<PaymentScheduleElement> paymentSchedule) {
            this.paymentSchedule = paymentSchedule;
            return this;
        }

        public Builder additionalServices(AdditionalServices additionalServices) {
            this.additionalServices = additionalServices;
            return this;
        }

        public Builder creditStatus(CreditStatus creditStatus) {
            this.creditStatus = creditStatus;
            return this;
        }

        public CreditDTO build() {
            return new CreditDTO(this);
        }
    }

    public CreditDTO() {
    }

    private CreditDTO(Builder builder) {
        this.amount = builder.amount;
        this.term = builder.term;
        this.monthlyPayment = builder.monthlyPayment;
        this.rate = builder.rate;
        this.psk = builder.psk;
        this.paymentSchedule = builder.paymentSchedule;
        this.additionalServices = builder.additionalServices;
        this.creditStatus = builder.creditStatus;
    }

    @Override
    public String toString() {
        String startString = "ID: " + id + ", Amount: " + amount + ", Term: " + term + ", Monthly payment: " + monthlyPayment + ", Rate: " + rate + ", PSK: " + psk + ", Payment schedule: ";
        StringBuilder sb = new StringBuilder("{ ");
        for (PaymentScheduleElement element : paymentSchedule) {
            sb.append(element).append("; ");
        }
        sb.append(" } ");
        String endString = "Additional services: " + additionalServices + ", Credit status: " + creditStatus;
        return startString + sb + endString;
    }
}
