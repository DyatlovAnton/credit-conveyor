package ru.neoflex.gateway.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Theme {
    FINISH_REGISTRATION("finish-registration"), CREATE_DOCUMENTS("create-documents"), SEND_DOCUMENTS("send-documents"), SEND_SES("send-ses"), CREDIT_ISSUED("credit-issued"), APPLICATION_DENIED("application-denied");
    private final String string;

    @JsonValue
    public String getString() {
        return string;
    }

    Theme(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return this.string;
    }

    @JsonCreator
    public static Theme fromString(String text) {
        for (Theme theme : Theme.values()) {
            if (theme.toString().equals(text)) {
                return theme;
            }
        }
        throw new IllegalArgumentException();
    }
}
