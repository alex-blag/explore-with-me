package ru.ewm.main.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EventBriefListResponseDto {

    private List<EventBriefResponseDto> events;

    private Long totalElements;

}
