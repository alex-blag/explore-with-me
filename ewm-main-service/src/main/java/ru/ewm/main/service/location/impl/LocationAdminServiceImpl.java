package ru.ewm.main.service.location.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ewm.main.dto.location.LocationCreateRequestDto;
import ru.ewm.main.dto.location.LocationUpdateRequestDto;
import ru.ewm.main.mapper.LocationMapper;
import ru.ewm.main.model.Location;
import ru.ewm.main.service.location.LocationAdminService;
import ru.ewm.main.service.location.LocationService;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LocationAdminServiceImpl implements LocationAdminService {

    private final LocationService locationService;
    private final LocationMapper locationMapper;

    @Override
    @Transactional
    public Location save(LocationCreateRequestDto locationCreateRequestDto) {
        Location location = locationMapper.toLocation(locationCreateRequestDto);
        return locationService.save(location);
    }

    @Override
    @Transactional
    public Location updateById(long id, LocationUpdateRequestDto locationUpdateRequestDto) {
        Location location = locationService.getByIdOrThrow(id);
        locationMapper.updateLocation(locationUpdateRequestDto, location);
        return location;
    }

    @Override
    public Location getByIdOrThrow(long id) {
        return locationService.getByIdOrThrow(id);
    }

    @Override
    public Page<Location> findAllByIds(List<Long> ids, Pageable pageable) {
        return locationService.findAllByIds(ids, pageable);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        locationService.deleteById(id);
    }

}
