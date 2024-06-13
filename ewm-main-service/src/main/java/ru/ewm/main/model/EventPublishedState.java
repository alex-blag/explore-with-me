package ru.ewm.main.model;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class EventPublishedState {

    LocalDateTime publishedOn;

    State state;

}
