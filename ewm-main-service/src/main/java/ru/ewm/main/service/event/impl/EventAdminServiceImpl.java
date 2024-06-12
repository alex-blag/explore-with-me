package ru.ewm.main.service.event.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ewm.main.dto.event.EventAdminParams;
import ru.ewm.main.dto.event.EventUpdateAdminRequestDto;
import ru.ewm.main.exception.ExceptionUtil;
import ru.ewm.main.mapper.EventMapper;
import ru.ewm.main.model.Category;
import ru.ewm.main.model.Event;
import ru.ewm.main.model.Location;
import ru.ewm.main.service.category.CategoryAdminService;
import ru.ewm.main.service.event.EventAdminService;
import ru.ewm.main.service.event.EventService;
import ru.ewm.main.service.location.LocationAdminService;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventAdminServiceImpl implements EventAdminService {

    private static final long ONE_HOUR_BEFORE_EARLY_START = 1;

    private final EventService eventService;
    private final EventMapper eventMapper;
    private final CategoryAdminService categoryAdminService;
    private final LocationAdminService locationAdminService;

    @Override
    @Transactional
    public Event updateById(long id, EventUpdateAdminRequestDto eventUpdateAdminRequestDto) {
        Event event = eventService.getByIdOrThrow(id);

        checkEventDateIsAfterEarlyStartOrThrow(
                id, eventUpdateAdminRequestDto.getEventDate(), ONE_HOUR_BEFORE_EARLY_START
        );

        Category category = getUpdatedCategoryOrCurrent(
                eventUpdateAdminRequestDto.getCategoryId(), event.getCategory()
        );

        Location location = getUpdatedLocationOrCurrent(
                eventUpdateAdminRequestDto.getLocationId(), event.getLocation()
        );

        // TODO -- update publishedOn and state

        eventMapper.updateEvent(eventUpdateAdminRequestDto, category, location, event);

        // TODO -- update confirmed requests and views ?

        return event;
    }

    @Override
    public Page<Event> findAllByParams(EventAdminParams eventParams, Pageable pageable) {
        return eventService.findAllByParams(
                eventParams.getInitiatorIds(),
                eventMapper.toStates(eventParams.getEventStates()),
                eventParams.getCategoryIds(),
                eventParams.getRangeBegin(),
                eventParams.getRangeEnd(),
                pageable
        );
    }

    private Location getUpdatedLocationOrCurrent(Long updateLocationId, Location location) {
        if (updateLocationId == null) {
            return location;
        }

        boolean isLocationIdNotNull = location != null && location.getId() != null;
        boolean isLocationIdNotEqualUpdateLocationId = isLocationIdNotNull && !location.getId().equals(updateLocationId);

        if (isLocationIdNotEqualUpdateLocationId) {
            return locationAdminService.getByIdOrThrow(updateLocationId);
        }

        return location;
    }

    private Category getUpdatedCategoryOrCurrent(Long updateCategoryId, Category category) {
        if (updateCategoryId == null) {
            return category;
        }

        boolean isCategoryIdNotNull = category != null && category.getId() != null;
        boolean isCategoryIdNotEqualUpdateCategoryId = isCategoryIdNotNull && !category.getId().equals(updateCategoryId);

        if (isCategoryIdNotEqualUpdateCategoryId) {
            return categoryAdminService.getByIdOrThrow(updateCategoryId);
        }

        return category;
    }

    private void checkEventDateIsAfterEarlyStartOrThrow(
            long eventId, LocalDateTime eventDate, long hoursBeforeEarlyStart
    ) {
        if (eventDate == null) {
            return;
        }

        LocalDateTime earlyStart = LocalDateTime.now().plusHours(hoursBeforeEarlyStart);
        if (eventDate.isBefore(earlyStart)) {
            throw ExceptionUtil.getEventDateIsTooEarlyException(eventId, eventDate);
        }
    }

}
