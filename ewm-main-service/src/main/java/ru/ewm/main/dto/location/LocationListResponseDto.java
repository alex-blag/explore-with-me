package ru.ewm.main.dto.location;

import lombok.Data;

import java.util.List;

@Data
public class LocationListResponseDto {

    private List<LocationResponseDto> locations;

    private Long totalElements;

}
