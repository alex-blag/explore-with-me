package ru.ewm.main.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ewm.main.dto.event.EventAdminParams;
import ru.ewm.main.dto.event.EventListResponseDto;
import ru.ewm.main.dto.event.EventResponseDto;
import ru.ewm.main.dto.event.EventStateDto;
import ru.ewm.main.dto.event.EventUpdateAdminRequestDto;
import ru.ewm.main.mapper.EventMapper;
import ru.ewm.main.model.event.Event;
import ru.ewm.main.service.event.EventAdminService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/admin/events")
@RequiredArgsConstructor
@Validated
public class EventAdminController {

    private final EventAdminService eventAdminService;
    private final EventMapper eventMapper;

    @PatchMapping("/{eventId}")
    public EventResponseDto updateEventById(
            @PathVariable long eventId,
            @RequestBody @Valid EventUpdateAdminRequestDto eventUpdateAdminRequestDto
    ) {
        Event event = eventAdminService.updateById(eventId, eventUpdateAdminRequestDto);
        return eventMapper.toEventResponseDto(event);
    }

    @GetMapping
    public EventListResponseDto getAllEventsByParams(
            @RequestParam(defaultValue = "") List<Long> initiatorIds,
            @RequestParam(defaultValue = "") List<EventStateDto> eventStates,
            @RequestParam(defaultValue = "") List<Long> categoryIds,
            @RequestParam(required = false) LocalDateTime rangeBegin,
            @RequestParam(required = false) LocalDateTime rangeEnd,
            @RequestParam(defaultValue = "0") @PositiveOrZero int page,
            @RequestParam(defaultValue = "10") @Positive int size
    ) {
        EventAdminParams params = new EventAdminParams(initiatorIds, eventStates, categoryIds, rangeBegin, rangeEnd);
        Pageable pageable = PageRequest.of(page, size);
        Page<Event> events = eventAdminService.findAllByParams(params, pageable);
        return eventMapper.toEventListResponseDto(events.getContent(), events.getTotalElements());
    }

}
