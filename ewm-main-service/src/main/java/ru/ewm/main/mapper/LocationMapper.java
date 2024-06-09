package ru.ewm.main.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.ewm.main.dto.location.LocationCreateRequestDto;
import ru.ewm.main.dto.location.LocationListResponseDto;
import ru.ewm.main.dto.location.LocationResponseDto;
import ru.ewm.main.dto.location.LocationUpdateRequestDto;
import ru.ewm.main.model.Location;

import java.util.List;

@Mapper
public interface LocationMapper {

    @Mapping(target = "id", ignore = true)
    Location toLocation(LocationCreateRequestDto locationCreateRequestDto);

    LocationResponseDto toLocationResponseDto(Location location);

    LocationListResponseDto toLocationListResponseDto(List<Location> locations, long totalElements);

    @BeanMapping(
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "id", ignore = true)
    void updateLocation(LocationUpdateRequestDto locationUpdateRequestDto, @MappingTarget Location location);

}
