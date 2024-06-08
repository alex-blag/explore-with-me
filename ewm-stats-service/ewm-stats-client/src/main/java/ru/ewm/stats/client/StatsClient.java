package ru.ewm.stats.client;

import ru.ewm.stats.dto.EndpointHitRequestDto;
import ru.ewm.stats.dto.EndpointHitResponseDto;
import ru.ewm.stats.dto.ViewStatsResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsClient {

    EndpointHitResponseDto saveHit(EndpointHitRequestDto endpointHitRequestDto);

    List<ViewStatsResponseDto> getStats(LocalDateTime begin, LocalDateTime end, List<String> uris, boolean unique);

}
