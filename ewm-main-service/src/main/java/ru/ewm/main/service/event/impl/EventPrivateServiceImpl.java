package ru.ewm.main.service.event.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ewm.main.dto.event.EventCreateUserRequestDto;
import ru.ewm.main.dto.event.EventUpdateUserRequestDto;
import ru.ewm.main.mapper.EventMapper;
import ru.ewm.main.model.Category;
import ru.ewm.main.model.Event;
import ru.ewm.main.model.Location;
import ru.ewm.main.model.State;
import ru.ewm.main.model.User;
import ru.ewm.main.service.category.CategoryPrivateService;
import ru.ewm.main.service.event.EventPrivateService;
import ru.ewm.main.service.event.EventService;
import ru.ewm.main.service.location.LocationPrivateService;
import ru.ewm.main.service.user.UserPrivateService;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventPrivateServiceImpl implements EventPrivateService {

    private static final long TWO_HOURS_BEFORE_EARLY_START = 2;

    private final EventService eventService;
    private final EventMapper eventMapper;
    private final UserPrivateService userPrivateService;
    private final CategoryPrivateService categoryPrivateService;
    private final LocationPrivateService locationPrivateService;

    @Override
    @Transactional
    public Event saveByInitiatorId(long initiatorId, EventCreateUserRequestDto eventCreateUserRequestDto) {
        EventUtil.checkEventDateAfterEarlyStartOrThrow(
                eventCreateUserRequestDto.getEventDate(), TWO_HOURS_BEFORE_EARLY_START
        );

        LocalDateTime createdOn = LocalDateTime.now();
        User initiator = userPrivateService.getByIdOrThrow(initiatorId);
        Category category = categoryPrivateService.getByIdOrThrow(eventCreateUserRequestDto.getCategoryId());
        Location location = locationPrivateService.getByIdOrThrow(eventCreateUserRequestDto.getLocationId());
        State state = State.PENDING;

        Event event = eventMapper.toEvent(eventCreateUserRequestDto, createdOn, initiator, category, location, state);

        return eventService.save(event);
    }

    @Override
    @Transactional
    public Event updateByIdAndInitiatorId(
            long id, long initiatorId, EventUpdateUserRequestDto eventUpdateUserRequestDto
    ) {
        Event event = eventService.getByIdAndInitiatorIdOrThrow(id, initiatorId);

        LocalDateTime eventDateUpdate = eventUpdateUserRequestDto.getEventDate();
        if (eventDateUpdate != null) {
            EventUtil.checkEventDateAfterEarlyStartOrThrow(id, eventDateUpdate, TWO_HOURS_BEFORE_EARLY_START);
        }

        EventUtil.checkEventNotPublishedYetOrThrow(id, event.getState());

        Category category = getUpdatedCategoryOrCurrent(
                eventUpdateUserRequestDto.getCategoryId(), event.getCategory()
        );

        Location location = getUpdatedLocationOrCurrent(
                eventUpdateUserRequestDto.getLocationId(), event.getLocation()
        );

        eventMapper.updateEvent(eventUpdateUserRequestDto, category, location, event);

        // TODO -- update confirmed requests and views ?

        return event;
    }

    @Override
    public Event getByIdAndInitiatorIdOrThrow(long id, long initiatorId) {
        return eventService.getByIdAndInitiatorIdOrThrow(id, initiatorId);
    }

    @Override
    public Page<Event> findAllByInitiatorId(long initiatorId, Pageable pageable) {
        return eventService.findAllByInitiatorId(initiatorId, pageable);
    }

    private Category getUpdatedCategoryOrCurrent(Long updatedCategoryId, Category currentCategory) {
        if (updatedCategoryId == null) {
            return currentCategory;
        }

        boolean isCategoryIdNotNull = currentCategory != null && currentCategory.getId() != null;
        boolean isCategoryIdNotEqualUpdateCategoryId = isCategoryIdNotNull && !currentCategory.getId().equals(updatedCategoryId);

        if (isCategoryIdNotEqualUpdateCategoryId) {
            return categoryPrivateService.getByIdOrThrow(updatedCategoryId);
        }

        return currentCategory;
    }

    private Location getUpdatedLocationOrCurrent(Long updatedLocationId, Location currentLocation) {
        if (updatedLocationId == null) {
            return currentLocation;
        }

        boolean isLocationIdNotNull = currentLocation != null && currentLocation.getId() != null;
        boolean isLocationIdNotEqualUpdateLocationId = isLocationIdNotNull && !currentLocation.getId().equals(updatedLocationId);

        if (isLocationIdNotEqualUpdateLocationId) {
            return locationPrivateService.getByIdOrThrow(updatedLocationId);
        }

        return currentLocation;
    }

}
