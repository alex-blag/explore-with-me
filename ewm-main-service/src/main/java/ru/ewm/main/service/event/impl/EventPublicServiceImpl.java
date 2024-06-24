package ru.ewm.main.service.event.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ewm.main.dto.event.EventPublicParams;
import ru.ewm.main.mapper.EventMapper;
import ru.ewm.main.model.Event;
import ru.ewm.main.service.event.EventPublicService;
import ru.ewm.main.service.event.EventService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventPublicServiceImpl implements EventPublicService {

    private final EventService eventService;
    private final EventMapper eventMapper;

    @Override
    public Event getByIdOrThrow(long id) {
        // TODO -- update confirmed requests and views ?
        return eventService.getByIdOrThrow(id);
    }

    @Override
    public Page<Event> findAllByParams(EventPublicParams params, Pageable pageable) {
        return eventService.findAllByParams(
                params.getSearch(),
                params.getCategoryIds(),
                params.getRangeBegin(),
                params.getRangeEnd(),
                params.getPaid(),
                eventMapper.toSorting(params.getSorting()),
                pageable);
    }

}
