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

    Page<Event> findAllByParams(
            List<Long> initiatorIds,
            List<State> states,
            List<Long> categoryIds,
            LocalDateTime rangeBegin,
            LocalDateTime rangeEnd,
            Pageable pageable
    );

}
