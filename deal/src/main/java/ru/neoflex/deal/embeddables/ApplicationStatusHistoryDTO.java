package ru.neoflex.deal.embeddables;

import lombok.Getter;
import ru.neoflex.deal.enums.ApplicationStatus;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Getter
@Embeddable
public class ApplicationStatusHistoryDTO {
    private ApplicationStatus applicationStatus;
    private LocalDateTime time;
    private ApplicationStatus changeType;

    public ApplicationStatusHistoryDTO(){};

    public ApplicationStatusHistoryDTO(ApplicationStatus applicationStatus, LocalDateTime time, ApplicationStatus changeType){
        this.applicationStatus = applicationStatus;
        this.time = time;
        this.changeType = changeType;
    }
}
