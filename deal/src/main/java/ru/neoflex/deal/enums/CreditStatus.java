package ru.neoflex.deal.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CreditStatus {
    CALCULATED("calculated"), ISSUED("issued");
    private final String string;

    @JsonValue
    public String getString() {
        return string;
    }

    CreditStatus(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return this.string;
    }

    @JsonCreator
    public static CreditStatus fromString(String text) {
        for (CreditStatus creditStatus : CreditStatus.values()) {
            if (creditStatus.toString().equals(text)) {
                return creditStatus;
            }
        }
        throw new IllegalArgumentException();
    }
}
