package ru.ewm.main.exception.request;

public class RequesterOwnsEventException extends RuntimeException {

    public RequesterOwnsEventException(String message) {
        super(message);
    }

}
