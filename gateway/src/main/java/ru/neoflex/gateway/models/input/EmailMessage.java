package ru.neoflex.gateway.models.input;

import ru.neoflex.gateway.models.enums.Theme;

public class EmailMessage {
    private final String address;
    private final Theme theme;
    private final Long applicationId;

    public EmailMessage(String address, Theme theme, Long applicationId) {
        this.address = address;
        this.theme = theme;
        this.applicationId = applicationId;
    }
}
