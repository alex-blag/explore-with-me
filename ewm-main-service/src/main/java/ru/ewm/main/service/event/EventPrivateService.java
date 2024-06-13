package ru.ewm.main.service.event;

import ru.ewm.main.dto.event.EventCreateUserRequestDto;
import ru.ewm.main.model.Event;

public interface EventPrivateService {

    Event saveByInitiatorId(long initiatorId, EventCreateUserRequestDto eventCreateUserRequestDto);

}
