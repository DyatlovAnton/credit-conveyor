package ru.neoflex.dossier.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import ru.neoflex.dossier.enums.Theme;

@Getter
@Schema(description = "Письмо клиенту")
public class EmailMessage {
    @Schema(description = "Адрес клиента")
    private final String address;
    @Schema(description = "Тема письма")
    private final Theme theme;
    @Schema(description = "ID заявки на кредит")
    private final Long applicationId;

    public EmailMessage(String address, Theme theme, Long applicationId) {
        this.address = address;
        this.theme = theme;
        this.applicationId = applicationId;
    }
}
