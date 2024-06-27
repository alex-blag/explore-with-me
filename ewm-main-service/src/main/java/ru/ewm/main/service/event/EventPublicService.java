package ru.ewm.main.service.event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.ewm.main.dto.event.EventPublicParams;
import ru.ewm.main.model.event.Event;

import java.util.List;

public interface EventPublicService {

    Event getByIdOrThrow(long id);

    Page<Event> findAllByParams(EventPublicParams params, Pageable pageable);

    void updateNumberOfConfirmedRequests(List<Event> events);

}
