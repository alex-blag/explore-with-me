package ru.ewm.main.service.location.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ewm.main.model.Location;
import ru.ewm.main.service.location.LocationPrivateService;
import ru.ewm.main.service.location.LocationService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LocationPrivateServiceImpl implements LocationPrivateService {

    private final LocationService locationService;

    @Override
    public Location getByIdOrThrow(long id) {
        return locationService.getByIdOrThrow(id);
    }

}
