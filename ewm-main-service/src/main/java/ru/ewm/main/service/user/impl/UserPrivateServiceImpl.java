package ru.ewm.main.service.user.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ewm.main.model.User;
import ru.ewm.main.service.user.UserPrivateService;
import ru.ewm.main.service.user.UserService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserPrivateServiceImpl implements UserPrivateService {

    private final UserService userService;

    @Override
    public boolean existsById(long id) {
        return userService.existsById(id);
    }

    @Override
    public User getByIdOrThrow(long id) {
        return userService.getByIdOrThrow(id);
    }

}
