package ru.ewm.main.exception.event;

public class EventAlreadyPublishedException extends RuntimeException {

    public EventAlreadyPublishedException(String message) {
        super(message);
    }

}
