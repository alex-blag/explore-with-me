package ru.ewm.stats.server.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ewm.stats.server.dto.EndpointHitRequestDto;
import ru.ewm.stats.server.dto.EndpointHitResponseDto;
import ru.ewm.stats.server.model.EndpointHit;
import ru.ewm.stats.server.model.ServiceApp;

@Mapper
public interface StatsMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "serviceApp", source = "serviceApp")
    EndpointHit toEndpointHit(EndpointHitRequestDto endpointHitRequestDto, ServiceApp serviceApp);

    @Mapping(target = "app", source = "serviceApp.name")
    EndpointHitResponseDto toEndpointHitResponseDto(EndpointHit endpointHit);

}
