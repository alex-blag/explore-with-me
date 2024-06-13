package ru.ewm.main.exception.event;

public class EventNotPendingException extends RuntimeException {

    public EventNotPendingException(String message) {
        super(message);
    }

}
