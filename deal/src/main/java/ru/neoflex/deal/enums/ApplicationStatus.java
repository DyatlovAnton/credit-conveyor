package ru.neoflex.deal.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ApplicationStatus {
    PREAPPROVAL("preapproval"), APPROVED("approved"), CC_DENIED("cc_denied"), CC_APPROVED("cc_approved"),
    PREPARE_DOCUMENTS("prepare_documents"), DOCUMENT_CREATED("document_created"), CLIENT_DENIED("client_denied"),
    DOCUMENT_SIGNED("document_signed"), CREDIT_ISSUED("credit_issued");
    private final String string;

    @JsonValue
    public String getString() {
        return string;
    }

    ApplicationStatus(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return this.string;
    }

    @JsonCreator
    public static ApplicationStatus fromString(String text) {
        for (ApplicationStatus applicationStatus : ApplicationStatus.values()) {
            if (applicationStatus.toString().equals(text)) {
                return applicationStatus;
            }
        }
        throw new IllegalArgumentException();
    }
}
