package ru.ewm.main.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.ewm.main.dto.category.CategoryBriefResponseDto;
import ru.ewm.main.dto.user.UserBriefResponseDto;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EventBriefResponseDto {

    private String annotation;

    private CategoryBriefResponseDto category;

    private LocalDateTime eventDate;

    private UserBriefResponseDto initiator;

    private Boolean paid;

    private String title;

    private Long confirmedRequests;

}
