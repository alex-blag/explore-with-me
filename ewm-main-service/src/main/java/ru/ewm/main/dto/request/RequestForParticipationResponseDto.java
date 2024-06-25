package ru.ewm.main.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class RequestForParticipationResponseDto {

    private Long id;

    private LocalDateTime created;

    private Long eventId;

    private Long requesterId;

    private RequestStatusDto status;

}
