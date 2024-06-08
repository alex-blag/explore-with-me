package ru.ewm.main.service.user;

import ru.ewm.main.model.User;

public interface UserPrivateService {

    boolean existsById(long id);

    User getByIdOrThrow(long id);

}
