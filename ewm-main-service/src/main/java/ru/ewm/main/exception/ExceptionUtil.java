package ru.ewm.main.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionUtil {

    public static CategoryNotFoundException getCategoryNotFoundException(long categoryId) {
        return new CategoryNotFoundException(
                String.format("%s [categoryId = %d]", ExceptionMessage.CATEGORY_NOT_FOUND, categoryId)
        );
    }

    public static UserNotFoundException getUserNotFoundException(long userId) {
        return new UserNotFoundException(
                String.format("%s [userId = %d]", ExceptionMessage.USER_NOT_FOUND, userId)
        );
    }

}
