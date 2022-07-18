package ru.neoflex.deal.embeddables;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class AdditionalServices {
    private boolean isInsuranceEnabled;
    private boolean isSalaryClient;

    public AdditionalServices(){}
    public AdditionalServices(boolean isInsuranceEnabled, boolean isSalaryClient) {
        this.isInsuranceEnabled = isInsuranceEnabled;
        this.isSalaryClient = isSalaryClient;
    }
}
