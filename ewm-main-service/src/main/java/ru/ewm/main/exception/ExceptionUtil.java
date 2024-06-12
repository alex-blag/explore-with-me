package ru.ewm.main.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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

    public static EventDateIsTooEarlyException getEventDateIsTooEarlyException(long eventId, LocalDateTime eventDate) {
        return new EventDateIsTooEarlyException(
                String.format(
                        "%s [eventId = %d, eventDate = %s]",
                        ExceptionMessage.EVENT_DATE_IS_TOO_EARLY,
                        eventId,
                        eventDate
                )
        );
    }

}
