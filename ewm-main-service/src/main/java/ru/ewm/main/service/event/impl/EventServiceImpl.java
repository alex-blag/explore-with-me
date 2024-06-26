package ru.ewm.main.service.event.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ewm.main.exception.ExceptionUtil;
import ru.ewm.main.model.event.Event;
import ru.ewm.main.model.event.EventSorting;
import ru.ewm.main.model.event.EventState;
import ru.ewm.main.repository.EventRepository;
import ru.ewm.main.service.event.EventService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    @Transactional
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event getByIdOrThrow(long id) {
        return eventRepository
                .findById(id)
                .orElseThrow(() -> ExceptionUtil.getEventNotFoundException(id));
    }

    @Override
    public Event getByIdAndInitiatorIdOrThrow(long id, long initiatorId) {
        return eventRepository
                .findByIdAndInitiatorId(id, initiatorId)
                .orElseThrow(() -> ExceptionUtil.getEventNotFoundException(id, initiatorId));
    }

    @Override
    public Page<Event> findAllByParams(
            List<Long> categoryIds,
            List<Long> initiatorIds,
            LocalDateTime rangeBegin,
            LocalDateTime rangeEnd,
            List<EventState> states,
            Pageable pageable
    ) {
        return eventRepository.findAllByParams(
                categoryIds,
                initiatorIds,
                rangeBegin,
                rangeEnd,
                states,
                pageable
        );
    }

    @Override
    public Page<Event> findAllByParams(
            String search,
            List<Long> categoryIds,
            LocalDateTime rangeBegin,
            LocalDateTime rangeEnd,
            Boolean paid,
            EventSorting sorting,
            Pageable pageable
    ) {
        return eventRepository.findAllByParams(
                search,
                categoryIds,
                rangeBegin,
                rangeEnd,
                paid,
                pageable
        );
    }

    @Override
    public Page<Event> findAllByInitiatorId(long initiatorId, Pageable pageable) {
        return eventRepository.findAllByInitiatorId(initiatorId, pageable);
    }

    @Override
    public List<Event> findAllByIds(List<Long> ids) {
        return eventRepository.findAllByIdIn(ids);
    }

}
