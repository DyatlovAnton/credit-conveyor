package ru.neoflex.deal.embeddables;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import ru.neoflex.deal.enums.ApplicationStatus;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Getter
@Embeddable
@Schema(description = "История статусов заявки")
public class ApplicationStatusHistoryDTO {
    @Schema(description = "Статус заявки")
    private ApplicationStatus applicationStatus;
    @Schema(description = "Время")
    private LocalDateTime time;
    @Schema(description = "Прежний статус заявки")
    private ApplicationStatus changeType;

    public ApplicationStatusHistoryDTO() {
    }

    public ApplicationStatusHistoryDTO(ApplicationStatus applicationStatus, LocalDateTime time, ApplicationStatus changeType) {
        this.applicationStatus = applicationStatus;
        this.time = time;
        this.changeType = changeType;
    }

    @Override
    public String toString() {
        return "Application status: " + applicationStatus + ", Time: " + time + ", Change type: " + changeType;
    }
}
