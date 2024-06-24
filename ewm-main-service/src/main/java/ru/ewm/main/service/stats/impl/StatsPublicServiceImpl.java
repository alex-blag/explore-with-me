package ru.ewm.main.service.stats.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ewm.main.service.stats.StatsPublicService;
import ru.ewm.main.service.stats.StatsService;
import ru.ewm.stats.dto.EndpointHitRequestDto;
import ru.ewm.stats.dto.EndpointHitResponseDto;

@Service
@RequiredArgsConstructor
public class StatsPublicServiceImpl implements StatsPublicService {

    private final StatsService statsService;

    @Override
    public EndpointHitResponseDto saveEndpointHit(EndpointHitRequestDto endpointHitRequestDto) {
        return statsService.saveEndpointHit(endpointHitRequestDto);
    }

}
