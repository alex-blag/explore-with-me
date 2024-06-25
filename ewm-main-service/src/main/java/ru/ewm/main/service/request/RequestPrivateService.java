package ru.ewm.main.service.request;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.ewm.main.model.request.Request;

public interface RequestPrivateService {

    Request saveByEventIdAndRequesterId(long eventId, long requesterId);

    Request cancelByIdAndRequesterId(long id, long requesterId);

    Page<Request> findAllByRequesterId(long requesterId, Pageable pageable);

}
