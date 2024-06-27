package ru.ewm.main.service.request;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.ewm.main.model.request.Request;
import ru.ewm.main.model.request.RequestTuple;

import java.util.List;

public interface RequestService {

    Request save(Request request);

    boolean existsByEventIdAndRequesterId(long eventId, long requesterId);

    long countConfirmedRequestsByEventId(long eventId);

    Request getByIdOrThrow(long id);

    Page<Request> findAllByRequesterId(long requesterId, Pageable pageable);

    List<RequestTuple> countAllConfirmedRequestsByEventIds(List<Long> eventIds);

}
