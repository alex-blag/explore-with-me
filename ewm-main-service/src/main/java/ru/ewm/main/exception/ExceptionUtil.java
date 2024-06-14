package ru.ewm.main.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.ewm.main.exception.event.EventAlreadyPublishedException;
import ru.ewm.main.exception.event.EventDateTooEarlyException;
import ru.ewm.main.exception.event.EventNotFoundException;
import ru.ewm.main.exception.event.EventNotPendingException;
import ru.ewm.main.model.State;

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

    public static EventNotPendingException getEventNotPendingException(long eventId, State state) {
        return new EventNotPendingException(
                String.format(
                        "%s [eventId = %d, state = %s]",
                        ExceptionMessage.EVENT_NOT_PENDING,
                        eventId,
                        state.name()
                )
        );
    }

    public static EventAlreadyPublishedException getEventAlreadyPublishedException(long eventId, State state) {
        return new EventAlreadyPublishedException(
                String.format(
                        "%s [eventId = %d, state = %s]",
                        ExceptionMessage.EVENT_ALREADY_PUBLISHED,
                        eventId,
                        state.name()
                )
        );
    }

}
