package ru.ewm.main.exception.request;

public class RequestAlreadyCreatedException extends RuntimeException {

    public RequestAlreadyCreatedException(String message) {
        super(message);
    }

}
