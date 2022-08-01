package ru.neoflex.dossier.models;

import lombok.Getter;
import ru.neoflex.dossier.enums.Theme;

@Getter
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
