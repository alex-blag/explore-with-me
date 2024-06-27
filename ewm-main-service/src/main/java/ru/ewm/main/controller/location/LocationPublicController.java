package ru.ewm.main.controller.location;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ewm.main.dto.location.LocationResponseDto;
import ru.ewm.main.mapper.LocationMapper;
import ru.ewm.main.model.Location;
import ru.ewm.main.service.location.LocationPublicService;

@RestController
@RequestMapping(path = "/locations")
@RequiredArgsConstructor
@Validated
public class LocationPublicController {

    private final LocationPublicService locationPublicService;
    private final LocationMapper locationMapper;

    @GetMapping("/{locationId}")
    public LocationResponseDto getLocationById(
            @PathVariable long locationId
    ) {
        Location location = locationPublicService.getByIdOrThrow(locationId);
        return locationMapper.toLocationResponseDto(location);
    }

}
