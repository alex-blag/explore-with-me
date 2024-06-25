package ru.ewm.main.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ewm.main.dto.event.EventBriefListResponseDto;
import ru.ewm.main.dto.event.EventPublicParams;
import ru.ewm.main.dto.event.EventPublicSorting;
import ru.ewm.main.dto.event.EventResponseDto;
import ru.ewm.main.mapper.EventMapper;
import ru.ewm.main.model.event.Event;
import ru.ewm.main.service.event.EventPublicService;
import ru.ewm.main.service.stats.StatsPublicService;
import ru.ewm.stats.dto.EndpointHitRequestDto;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/events")
@RequiredArgsConstructor
@Validated
public class EventPublicController {

    private static final String EWM_MAIN_SERVICE = "ewm-main-service";

    private final EventPublicService eventPublicService;
    private final EventMapper eventMapper;
    private final StatsPublicService statsPublicService;

    @GetMapping("/{eventId}")
    public EventResponseDto getEventById(
            @PathVariable long eventId,
            HttpServletRequest httpServletRequest
    ) {
        saveEndpointHit(httpServletRequest.getRequestURI(), httpServletRequest.getRemoteAddr());
        Event event = eventPublicService.getByIdOrThrow(eventId);
        return eventMapper.toEventResponseDto(event);
    }

    @GetMapping
    public EventBriefListResponseDto getAllEventsByParams(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "") List<Long> categoryIds,
            @RequestParam(required = false) LocalDateTime rangeBegin,
            @RequestParam(required = false) LocalDateTime rangeEnd,
            @RequestParam(required = false) Boolean paid,
            @RequestParam(required = false) EventPublicSorting sorting,
            @RequestParam(defaultValue = "0") @PositiveOrZero int page,
            @RequestParam(defaultValue = "10") @Positive int size,
            HttpServletRequest httpServletRequest
    ) {
        saveEndpointHit(httpServletRequest.getRequestURI(), httpServletRequest.getRemoteAddr());
        EventPublicParams params = new EventPublicParams(
                search, categoryIds, rangeBegin, rangeEnd, paid, sorting
        );
        Pageable pageable = PageRequest.of(page, size);
        Page<Event> events = eventPublicService.findAllByParams(params, pageable);
        return eventMapper.toEventBriefListResponseDto(events.getContent(), events.getTotalElements());
    }

    private void saveEndpointHit(String uri, String ip) {
        EndpointHitRequestDto endpointHit = buildEndpointHitRequestDto(uri, ip);
        statsPublicService.saveEndpointHit(endpointHit);
    }

    private EndpointHitRequestDto buildEndpointHitRequestDto(String uri, String ip) {
        return new EndpointHitRequestDto(EWM_MAIN_SERVICE, uri, ip, LocalDateTime.now());
    }

}
