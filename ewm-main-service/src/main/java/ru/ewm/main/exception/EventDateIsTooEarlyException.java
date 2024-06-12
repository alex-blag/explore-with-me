package ru.ewm.main.exception;

public class EventDateIsTooEarlyException extends RuntimeException {

    public EventDateIsTooEarlyException(String message) {
        super(message);
    }

}
