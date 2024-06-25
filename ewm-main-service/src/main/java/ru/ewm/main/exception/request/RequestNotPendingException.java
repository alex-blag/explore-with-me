package ru.ewm.main.exception.request;

public class RequestNotPendingException extends RuntimeException {

    public RequestNotPendingException(String message) {
        super(message);
    }

}
