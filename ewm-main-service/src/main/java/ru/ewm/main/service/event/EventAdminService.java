package ru.ewm.main.service.event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.ewm.main.dto.event.EventAdminParams;
import ru.ewm.main.dto.event.EventUpdateAdminRequestDto;
import ru.ewm.main.model.event.Event;

import java.util.List;

public interface EventAdminService {

    Event updateById(long id, EventUpdateAdminRequestDto eventUpdateAdminRequestDto);

    Page<Event> findAllByParams(EventAdminParams eventParams, Pageable pageable);

    List<Event> findAllByIds(List<Long> ids);

}
