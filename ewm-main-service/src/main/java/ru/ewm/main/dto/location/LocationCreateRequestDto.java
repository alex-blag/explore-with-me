package ru.ewm.main.dto.location;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LocationCreateRequestDto {

    @NotBlank
    @Size(max = 120)
    private String name;

    @NotNull
    private Double lat;

    @NotNull
    private Double lon;

    @NotBlank
    @Size(max = 7000)
    private String description;

}
