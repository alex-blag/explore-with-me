package ru.ewm.main.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.ewm.main.exception.event.EventAlreadyPublishedException;
import ru.ewm.main.exception.event.EventDateTooEarlyException;
import ru.ewm.main.exception.event.EventNotFoundException;
import ru.ewm.main.exception.event.EventNotPendingException;
import ru.ewm.main.exception.event.EventNotPublishedException;
import ru.ewm.main.exception.event.EventParticipantLimitReachedException;
import ru.ewm.main.exception.request.RequestAlreadyCreatedException;
import ru.ewm.main.exception.request.RequesterOwnsEventException;
import ru.ewm.main.model.event.EventState;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionUtil {

    public static UserNotFoundException getUserNotFoundException(long userId) {
        return new UserNotFoundException(
                String.format("%s [userId = %d]", ExceptionMessage.USER_NOT_FOUND, userId)
        );
    }

    public static CategoryNotFoundException getCategoryNotFoundException(long categoryId) {
        return new CategoryNotFoundException(
                String.format("%s [categoryId = %d]", ExceptionMessage.CATEGORY_NOT_FOUND, categoryId)
        );
    }

    public static LocationNotFoundException getLocationNotFoundException(long locationId) {
        return new LocationNotFoundException(
                String.format("%s [locationId = %d]", ExceptionMessage.LOCATION_NOT_FOUND, locationId)
        );
    }

    public static EventNotFoundException getEventNotFoundException(long eventId) {
        return new EventNotFoundException(
                String.format("%s [eventId = %d]", ExceptionMessage.EVENT_NOT_FOUND, eventId)
        );
    }

    public static EventNotFoundException getEventNotFoundException(long eventId, long initiatorId) {
        return new EventNotFoundException(
                String.format("%s [eventId = %d, initiatorId = %d]",
                        ExceptionMessage.EVENT_NOT_FOUND,
                        eventId,
                        initiatorId
                )
        );
    }

    public static EventDateTooEarlyException getEventDateTooEarlyException(long eventId, LocalDateTime eventDate) {
        return new EventDateTooEarlyException(
                String.format(
                        "%s [eventId = %d, eventDate = %s]",
                        ExceptionMessage.EVENT_DATE_TOO_EARLY,
                        eventId,
                        eventDate
                )
        );
    }

    public static EventNotPendingException getEventNotPendingException(long eventId, EventState state) {
        return new EventNotPendingException(
                String.format(
                        "%s [eventId = %d, state = %s]",
                        ExceptionMessage.EVENT_NOT_PENDING,
                        eventId,
                        state.name()
                )
        );
    }

    public static EventAlreadyPublishedException getEventAlreadyPublishedException(long eventId, EventState state) {
        return new EventAlreadyPublishedException(
                String.format(
                        "%s [eventId = %d, state = %s]",
                        ExceptionMessage.EVENT_ALREADY_PUBLISHED,
                        eventId,
                        state.name()
                )
        );
    }

    public static RequestAlreadyCreatedException getRequestAlreadyCreatedException(long eventId, long requesterId) {
        return new RequestAlreadyCreatedException(
                String.format(
                        "%s [eventId = %d, requesterId = %d]",
                        ExceptionMessage.REQUEST_ALREADY_CREATED,
                        eventId,
                        requesterId
                )
        );
    }

    public static RequesterOwnsEventException getRequesterOwnsEventException(long requesterId, long eventId) {
        return new RequesterOwnsEventException(
                String.format("%s [requesterId = %d, eventId = %d]",
                        ExceptionMessage.REQUESTER_OWNS_EVENT,
                        requesterId,
                        eventId
                )
        );
    }

    public static EventNotPublishedException getEventNotPublishedException(long eventId, EventState state) {
        return new EventNotPublishedException(
                String.format(
                        "%s [eventId = %d, state = %s]",
                        ExceptionMessage.EVENT_NOT_PUBLISHED,
                        eventId,
                        state.name()
                )
        );
    }

    public static EventParticipantLimitReachedException getEventParticipantLimitReachedException(
            long eventId,
            int participantLimit
    ) {
        return new EventParticipantLimitReachedException(
                String.format(
                        "%s [eventId = %d, participantLimit = %d]",
                        ExceptionMessage.EVENT_PARTICIPANT_LIMIT_REACHED,
                        eventId,
                        participantLimit
                )
        );
    }

}
