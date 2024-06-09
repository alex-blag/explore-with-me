package ru.ewm.main.dto.location;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class LocationResponseDto {

    private Long id;

    @Size(max = 120)
    private String name;

    private Double lat;

    private Double lon;

    @Size(max = 7000)
    private String description;

}
