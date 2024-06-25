package ru.ewm.main.service.request;

import ru.ewm.main.model.Request;

public interface RequestService {

    Request save(Request request);

    boolean existsByEventIdAndRequesterId(long eventId, long requesterId);

    long countConfirmedRequestsByEventId(long eventId);

}
