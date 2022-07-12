package ru.neoflex.deal.embeddables;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class AdditionalServices {
    private boolean isInsuranceEnabled;
    private boolean isSalaryClient;
}
