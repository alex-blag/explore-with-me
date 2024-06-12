package ru.ewm.main.dto.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ewm.main.dto.category.CategoryResponseDto;
import ru.ewm.main.dto.location.LocationResponseDto;
import ru.ewm.main.dto.user.UserResponseDto;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class EventResponseDto {

    private Long id;

    private String annotation;

    private CategoryResponseDto category;

    private LocalDateTime createdOn;

    private String description;

    private LocalDateTime eventDate;

    private UserResponseDto initiator;

    private LocationResponseDto location;

    private Boolean paid;

    private Integer participantLimit;

    private LocalDateTime publishedOn;

    private Boolean requestModeration;

    private EventState state;

    private String title;

}
