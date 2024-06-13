package ru.ewm.main.exception.event;

public class EventDateTooEarlyException extends RuntimeException {

    public EventDateTooEarlyException(String message) {
        super(message);
    }

}
