package ru.neoflex.deal.kafka;

import ru.neoflex.deal.enums.Theme;

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
