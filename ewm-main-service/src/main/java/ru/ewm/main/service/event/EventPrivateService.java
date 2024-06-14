package ru.ewm.main.service.event;

import ru.ewm.main.dto.event.EventCreateUserRequestDto;
import ru.ewm.main.dto.event.EventUpdateUserRequestDto;
import ru.ewm.main.model.Event;

public interface EventPrivateService {

    Event saveByInitiatorId(long initiatorId, EventCreateUserRequestDto eventCreateUserRequestDto);

    Event updateByIdAndInitiatorId(long id, long initiatorId, EventUpdateUserRequestDto eventUpdateUserRequestDto);

    Event getByIdAndInitiatorIdOrThrow(long eventId, long initiatorId);

}
