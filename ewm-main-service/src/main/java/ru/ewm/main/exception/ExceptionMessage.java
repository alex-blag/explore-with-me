package ru.ewm.main.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionMessage {

    public static final String USER_NOT_FOUND = "User not found";

    public static final String CATEGORY_NOT_FOUND = "Category not found";

    public static final String LOCATION_NOT_FOUND = "Location not found";

    public static final String EVENT_NOT_FOUND = "Event not found";

}
