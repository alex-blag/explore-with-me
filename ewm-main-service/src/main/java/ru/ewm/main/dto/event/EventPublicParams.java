package ru.ewm.main.dto.event;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
public class EventPublicParams {

    String search;

    List<Long> categoryIds;

    LocalDateTime rangeBegin;

    LocalDateTime rangeEnd;

    Boolean paid;

    EventPublicSorting sorting;

}
