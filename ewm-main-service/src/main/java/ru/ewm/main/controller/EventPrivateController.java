package ru.ewm.main.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.ewm.main.dto.event.EventCreateUserRequestDto;
import ru.ewm.main.dto.event.EventResponseDto;
import ru.ewm.main.mapper.EventMapper;
import ru.ewm.main.model.Event;
import ru.ewm.main.service.event.EventPrivateService;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/users/{userId}/events")
@RequiredArgsConstructor
@Validated
public class EventPrivateController {

    private final EventPrivateService eventPrivateService;
    private final EventMapper eventMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventResponseDto createEvent(
            @PathVariable long userId,
            @RequestBody @Valid EventCreateUserRequestDto eventCreateUserRequestDto
    ) {
        Event event = eventPrivateService.saveByInitiatorId(userId, eventCreateUserRequestDto);
        return eventMapper.toEventResponseDto(event);
    }

}
