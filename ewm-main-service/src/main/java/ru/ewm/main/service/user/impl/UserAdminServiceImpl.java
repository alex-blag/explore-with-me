package ru.ewm.main.service.user.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ewm.main.dto.user.UserCreateRequestDto;
import ru.ewm.main.mapper.UserMapper;
import ru.ewm.main.model.User;
import ru.ewm.main.service.user.UserAdminService;
import ru.ewm.main.service.user.UserService;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserAdminServiceImpl implements UserAdminService {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public User save(UserCreateRequestDto userCreateRequestDto) {
        User user = userMapper.toUser(userCreateRequestDto);
        return userService.save(user);
    }

    @Override
    public Page<User> findAllByIds(List<Long> ids, Pageable pageable) {
        return userService.findAllByIds(ids, pageable);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        userService.deleteById(id);
    }

}
