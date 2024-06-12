package ru.ewm.main.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class EventUpdateAdminRequestDto {

    @Size(min = 20, max = 2000)
    private String annotation;

    private Long categoryId;

    @Size(min = 20, max = 7000)
    private String description;

    private LocalDateTime eventDate;

    private Long locationId;

    private Boolean paid;

    @PositiveOrZero
    private Integer participantLimit;

    private Boolean requestModeration;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private EventStateAdminAction stateAction;

    @Size(min = 3, max = 120)
    private String title;

}
