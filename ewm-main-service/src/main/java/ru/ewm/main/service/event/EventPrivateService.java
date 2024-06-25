package ru.ewm.main.service.event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.ewm.main.dto.event.EventCreateUserRequestDto;
import ru.ewm.main.dto.event.EventUpdateUserRequestDto;
import ru.ewm.main.model.event.Event;

public interface EventPrivateService {

    Event saveByInitiatorId(long initiatorId, EventCreateUserRequestDto eventCreateUserRequestDto);

    Event updateByIdAndInitiatorId(long id, long initiatorId, EventUpdateUserRequestDto eventUpdateUserRequestDto);

    Event getByIdOrThrow(long id);

    Event getByIdAndInitiatorIdOrThrow(long id, long initiatorId);

    Page<Event> findAllByInitiatorId(long initiatorId, Pageable pageable);

}
