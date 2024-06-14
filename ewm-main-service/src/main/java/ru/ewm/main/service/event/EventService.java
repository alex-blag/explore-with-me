package ru.ewm.main.service.event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.ewm.main.model.Event;
import ru.ewm.main.model.State;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {

    Event save(Event event);

    Event getByIdOrThrow(long id);

    Event getByIdAndInitiatorIdOrThrow(long id, long initiatorId);

    Page<Event> findAllByParams(
            List<Long> categoryIds,
            List<Long> initiatorIds,
            LocalDateTime rangeBegin,
            LocalDateTime rangeEnd,
            List<State> states,
            Pageable pageable
    );

    Page<Event> findAllByInitiatorId(long initiatorId, Pageable pageable);

}
