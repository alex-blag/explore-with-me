package ru.ewm.main.dto.event;

import lombok.Data;

import java.util.List;

@Data
public class EventListResponseDto {

    private List<EventResponseDto> events;

    private Long totalElements;

}
