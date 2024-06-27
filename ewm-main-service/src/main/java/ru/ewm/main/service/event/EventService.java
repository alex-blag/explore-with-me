package ru.ewm.main.service.event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.ewm.main.model.event.Event;
import ru.ewm.main.model.event.EventSorting;
import ru.ewm.main.model.event.EventState;

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
            List<EventState> states,
            Pageable pageable
    );

    Page<Event> findAllByParams(
            String search,
            List<Long> categoryIds,
            LocalDateTime rangeBegin,
            LocalDateTime rangeEnd,
            Boolean paid,
            EventSorting sorting,
            Pageable pageable
    );

    Page<Event> findAllByInitiatorId(long initiatorId, Pageable pageable);

    List<Event> findAllByIds(List<Long> ids);

    void updateNumberOfConfirmedRequests(List<Event> events);

    boolean existsByCategoryId(long categoryId);

}
