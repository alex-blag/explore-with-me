package ru.ewm.main.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ewm.main.dto.location.LocationCreateRequestDto;
import ru.ewm.main.dto.location.LocationListResponseDto;
import ru.ewm.main.dto.location.LocationResponseDto;
import ru.ewm.main.dto.location.LocationUpdateRequestDto;
import ru.ewm.main.mapper.LocationMapper;
import ru.ewm.main.model.Location;
import ru.ewm.main.service.location.LocationAdminService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping(path = "/admin/locations")
@RequiredArgsConstructor
@Validated
public class LocationAdminController {

    private final LocationAdminService locationAdminService;
    private final LocationMapper locationMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LocationResponseDto createLocation(
            @RequestBody @Valid LocationCreateRequestDto locationCreateRequestDto
    ) {
        Location location = locationAdminService.save(locationCreateRequestDto);
        return locationMapper.toLocationResponseDto(location);
    }

    @PatchMapping("/{locationId}")
    public LocationResponseDto updateLocationById(
            @PathVariable long locationId,
            @RequestBody @Valid LocationUpdateRequestDto locationUpdateRequestDto
    ) {
        Location location = locationAdminService.updateById(locationId, locationUpdateRequestDto);
        return locationMapper.toLocationResponseDto(location);
    }

    @GetMapping
    public LocationListResponseDto getAllLocationsByParams(
            @RequestParam(defaultValue = "") List<Long> locationIds,
            @RequestParam(defaultValue = "0") @PositiveOrZero int page,
            @RequestParam(defaultValue = "10") @Positive int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Location> locations = locationAdminService.findAllByIds(locationIds, pageable);
        return locationMapper.toLocationListResponseDto(locations.getContent(), locations.getTotalElements());
    }

    @DeleteMapping("/{locationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLocationById(
            @PathVariable long locationId
    ) {
        locationAdminService.deleteById(locationId);
    }

}
