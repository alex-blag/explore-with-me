package ru.ewm.main.service.event.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ewm.main.dto.event.EventAdminParams;
import ru.ewm.main.dto.event.EventStateAdminAction;
import ru.ewm.main.dto.event.EventUpdateAdminRequestDto;
import ru.ewm.main.exception.ExceptionUtil;
import ru.ewm.main.mapper.EventMapper;
import ru.ewm.main.model.Category;
import ru.ewm.main.model.Event;
import ru.ewm.main.model.EventPublishedState;
import ru.ewm.main.model.Location;
import ru.ewm.main.model.State;
import ru.ewm.main.service.category.CategoryAdminService;
import ru.ewm.main.service.event.EventAdminService;
import ru.ewm.main.service.event.EventService;
import ru.ewm.main.service.location.LocationAdminService;

import java.time.LocalDateTime;
import java.util.List;

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

        LocalDateTime eventDate = eventUpdateAdminRequestDto.getEventDate();
        if (eventDate != null) {
            EventUtil.checkEventDateAfterEarlyStartOrThrow(id, eventDate, ONE_HOUR_BEFORE_EARLY_START);
        }

        Category category = getUpdatedCategoryOrCurrent(
                eventUpdateAdminRequestDto.getCategoryId(), event.getCategory()
        );

        Location location = getUpdatedLocationOrCurrent(
                eventUpdateAdminRequestDto.getLocationId(), event.getLocation()
        );

        EventPublishedState eventPublishedState = getEventPublishedState(
                event.getId(), event.getState(), eventUpdateAdminRequestDto.getStateAction()
        );

        eventMapper.updateEvent(eventUpdateAdminRequestDto, category, location, eventPublishedState, event);

        // TODO -- update confirmed requests and views ?

        return event;
    }

    @Override
    public Page<Event> findAllByParams(EventAdminParams eventParams, Pageable pageable) {
        List<State> states = eventMapper.toStates(eventParams.getEventStates());

        return eventService.findAllByParams(
                eventParams.getCategoryIds(),
                eventParams.getInitiatorIds(),
                eventParams.getRangeBegin(),
                eventParams.getRangeEnd(),
                states,
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

    private EventPublishedState getEventPublishedState(
            long eventId, State currentState, EventStateAdminAction eventStateAdminAction
    ) {
        if (eventStateAdminAction == null) {
            return new EventPublishedState(null, null);
        }

        LocalDateTime publishedOn;
        State updatedState;

        switch (eventStateAdminAction) {
            case PUBLISH_EVENT:
                checkEventPendingOrThrow(eventId, currentState);
                publishedOn = LocalDateTime.now();
                updatedState = State.PUBLISHED;
                break;

            case REJECT_EVENT:
                EventUtil.checkEventNotPublishedYetOrThrow(eventId, currentState);
                publishedOn = null;
                updatedState = State.CANCELED;
                break;

            default:
                throw new UnsupportedOperationException(eventStateAdminAction.name());
        }

        return new EventPublishedState(publishedOn, updatedState);
    }

    private void checkEventPendingOrThrow(long eventId, State state) {
        if (state != State.PENDING) {
            throw ExceptionUtil.getEventNotPendingException(eventId, state);
        }
    }

}
