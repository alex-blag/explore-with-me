package ru.ewm.main.exception.event;

public class EventParticipantLimitReachedException extends RuntimeException {

    public EventParticipantLimitReachedException(String message) {
        super(message);
    }

}
