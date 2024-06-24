package ru.ewm.main.service.stats.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ewm.main.service.stats.StatsService;
import ru.ewm.stats.client.StatsClient;
import ru.ewm.stats.dto.EndpointHitRequestDto;
import ru.ewm.stats.dto.EndpointHitResponseDto;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {

    private final StatsClient statsClient;

    @Override
    public EndpointHitResponseDto saveEndpointHit(EndpointHitRequestDto endpointHitRequestDto) {
        return statsClient.saveEndpointHit(endpointHitRequestDto);
    }

}
