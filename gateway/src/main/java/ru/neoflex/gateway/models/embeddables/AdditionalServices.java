package ru.neoflex.gateway.models.embeddables;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "Дополнительная информация")
public class AdditionalServices {
    @Schema(description = "Включена ли страховка")
    private boolean isInsuranceEnabled;
    @Schema(description = "Включен ли зарплатный клиент")
    private boolean isSalaryClient;

    public AdditionalServices() {
    }

    public AdditionalServices(boolean isInsuranceEnabled, boolean isSalaryClient) {
        this.isInsuranceEnabled = isInsuranceEnabled;
        this.isSalaryClient = isSalaryClient;
    }

    @Override
    public String toString() {
        return "Is insurance enabled: " + isInsuranceEnabled + ", Is salary client: " + isSalaryClient;
    }
}
