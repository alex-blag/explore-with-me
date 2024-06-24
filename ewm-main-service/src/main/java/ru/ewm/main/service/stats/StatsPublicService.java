package ru.ewm.main.service.stats;

import ru.ewm.stats.dto.EndpointHitRequestDto;
import ru.ewm.stats.dto.EndpointHitResponseDto;

public interface StatsPublicService {

    EndpointHitResponseDto saveEndpointHit(EndpointHitRequestDto endpointHitRequestDto);

}
