package ru.ewm.stats.server.service;

import ru.ewm.stats.server.dto.EndpointHitRequestDto;
import ru.ewm.stats.server.model.EndpointHit;
import ru.ewm.stats.server.model.ViewStats;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsService {

    EndpointHit save(EndpointHitRequestDto endpointHitRequestDto);

    List<ViewStats> findAll(LocalDateTime begin, LocalDateTime end, List<String> uris, boolean unique);

}
