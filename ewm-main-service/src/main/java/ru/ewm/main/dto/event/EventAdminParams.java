package ru.ewm.main.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class EventAdminParams {

    List<Long> initiatorIds;

    List<EventState> eventStates;

    List<Long> categoryIds;

    LocalDateTime rangeBegin;

    LocalDateTime rangeEnd;

}
