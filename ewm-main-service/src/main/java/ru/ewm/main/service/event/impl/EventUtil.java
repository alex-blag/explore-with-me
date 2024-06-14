package ru.ewm.main.service.event.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.ewm.main.exception.ExceptionUtil;
import ru.ewm.main.model.State;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EventUtil {

    public static final int NON_EXISTENT_DEFAULT_EVENT_ID = -1;

    public static void checkEventDateAfterEarlyStartOrThrow(
            long eventId, LocalDateTime eventDate, long hoursBeforeEarlyStart
    ) {
        LocalDateTime earlyStart = LocalDateTime.now().plusHours(hoursBeforeEarlyStart);
        if (eventDate.isBefore(earlyStart)) {
            throw ExceptionUtil.getEventDateTooEarlyException(eventId, eventDate);
        }
    }

    public static void checkEventDateAfterEarlyStartOrThrow(LocalDateTime eventDate, long hoursBeforeEarlyStart) {
        checkEventDateAfterEarlyStartOrThrow(NON_EXISTENT_DEFAULT_EVENT_ID, eventDate, hoursBeforeEarlyStart);
    }

    public static void checkEventNotPublishedYetOrThrow(long eventId, State state) {
        if (state == State.PUBLISHED) {
            throw ExceptionUtil.getEventAlreadyPublishedException(eventId, state);
        }
    }

}
