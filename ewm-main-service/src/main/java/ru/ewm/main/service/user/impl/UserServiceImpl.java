package ru.ewm.main.service.user.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ewm.main.exception.ExceptionUtil;
import ru.ewm.main.model.User;
import ru.ewm.main.repository.UserRepository;
import ru.ewm.main.service.user.UserService;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean existsById(long id) {
        return userRepository.existsById(id);
    }

    @Override
    public User getByIdOrThrow(long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> ExceptionUtil.getUserNotFoundException(id));
    }

    @Override
    public Page<User> findAllByIds(List<Long> ids, Pageable pageable) {
        return userRepository.findAllByIdIn(ids, pageable);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        checkUserExistsOrThrow(id);
        userRepository.deleteById(id);
    }

    private void checkUserExistsOrThrow(long id) {
        if (!userRepository.existsById(id)) {
            throw ExceptionUtil.getUserNotFoundException(id);
        }
    }

}
