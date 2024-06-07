package ru.ewm.stats.common;

import ru.ewm.stats.common.dto.EndpointHitRequestDto;
import ru.ewm.stats.common.dto.EndpointHitResponseDto;
import ru.ewm.stats.common.dto.ViewStatsResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsClient {

    EndpointHitResponseDto saveHit(EndpointHitRequestDto endpointHitRequestDto);

    List<ViewStatsResponseDto> getStats(LocalDateTime begin, LocalDateTime end, List<String> uris, boolean unique);

}
