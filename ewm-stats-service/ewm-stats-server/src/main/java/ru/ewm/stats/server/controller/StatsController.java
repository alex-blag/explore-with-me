package ru.ewm.stats.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.ewm.stats.server.dto.EndpointHitRequestDto;
import ru.ewm.stats.server.dto.EndpointHitResponseDto;
import ru.ewm.stats.server.dto.ViewStatsResponseDto;
import ru.ewm.stats.server.mapper.StatsMapper;
import ru.ewm.stats.server.model.EndpointHit;
import ru.ewm.stats.server.model.ViewStats;
import ru.ewm.stats.server.service.StatsService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StatsController {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private final StatsService statsService;
    private final StatsMapper statsMapper;

    @PostMapping("/hit")
    @ResponseStatus(HttpStatus.CREATED)
    public EndpointHitResponseDto createEndpointHit(
            @Valid @RequestBody EndpointHitRequestDto endpointHitRequestDto
    ) {
        EndpointHit endpointHit = statsService.save(endpointHitRequestDto);
        return statsMapper.toEndpointHitResponseDto(endpointHit);
    }

    @GetMapping("/stats")
    public List<ViewStatsResponseDto> getAllViewStatsByParams(
            @RequestParam @DateTimeFormat(pattern = DATE_TIME_PATTERN) LocalDateTime begin,
            @RequestParam @DateTimeFormat(pattern = DATE_TIME_PATTERN) LocalDateTime end,
            @RequestParam(defaultValue = "") List<String> uris,
            @RequestParam(defaultValue = "false") boolean unique
    ) {
        List<ViewStats> viewStats = statsService.findAll(begin, end, uris, unique);
        return statsMapper.toViewStatsResponseDtos(viewStats);
    }

}
