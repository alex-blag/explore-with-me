package ru.ewm.main.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.ewm.main.dto.event.EventCreateUserRequestDto;
import ru.ewm.main.dto.event.EventResponseDto;
import ru.ewm.main.dto.event.EventUpdateUserRequestDto;
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
    public EventResponseDto createEventByInitiatorId(
            @PathVariable long userId,
            @RequestBody @Valid EventCreateUserRequestDto eventCreateUserRequestDto
    ) {
        Event event = eventPrivateService.saveByInitiatorId(userId, eventCreateUserRequestDto);
        return eventMapper.toEventResponseDto(event);
    }

    @PatchMapping("/{eventId}")
    public EventResponseDto updateEventByInitiatorIdAndEventId(
            @PathVariable long userId,
            @PathVariable long eventId,
            @RequestBody @Valid EventUpdateUserRequestDto eventUpdateUserRequestDto
    ) {
        Event event = eventPrivateService.updateByIdAndInitiatorId(eventId, userId, eventUpdateUserRequestDto);
        return eventMapper.toEventResponseDto(event);
    }

    @GetMapping("/{eventId}")
    public EventResponseDto getEventByInitiatorIdAndEventId(
            @PathVariable long userId,
            @PathVariable long eventId
    ) {
        Event event = eventPrivateService.getByIdAndInitiatorIdOrThrow(eventId, userId);
        return eventMapper.toEventResponseDto(event);
    }

}
