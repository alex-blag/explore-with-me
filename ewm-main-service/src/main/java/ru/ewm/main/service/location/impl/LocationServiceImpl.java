package ru.ewm.main.service.location.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ewm.main.exception.ExceptionUtil;
import ru.ewm.main.model.Location;
import ru.ewm.main.repository.LocationRepository;
import ru.ewm.main.service.event.EventService;
import ru.ewm.main.service.location.LocationService;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final EventService eventService;

    @Override
    @Transactional
    public Location save(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location getByIdOrThrow(long id) {
        return locationRepository
                .findById(id)
                .orElseThrow(() -> ExceptionUtil.getLocationNotFoundException(id));
    }

    @Override
    public Page<Location> findAllByIds(List<Long> ids, Pageable pageable) {
        return locationRepository.findAllByIdIn(ids, pageable);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        checkLocationExistsOrThrow(id);
        checkLocationHasNoAssociatedEventsOrThrow(id);
        locationRepository.deleteById(id);
    }

    private void checkLocationExistsOrThrow(long id) {
        if (!locationRepository.existsById(id)) {
            throw ExceptionUtil.getLocationNotFoundException(id);
        }
    }

    private void checkLocationHasNoAssociatedEventsOrThrow(long id) {
        if (eventService.existsByLocationId(id)) {
            throw ExceptionUtil.getLocationHasAssociatedEventsException(id);
        }
    }

}
