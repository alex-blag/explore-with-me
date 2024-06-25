package ru.ewm.main.model.event;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class EventPublishedState {

    LocalDateTime publishedOn;

    EventState state;

}
