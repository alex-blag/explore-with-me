package ru.ewm.main.service.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.ewm.main.model.User;

import java.util.List;

public interface UserService {

    User save(User user);

    boolean existsById(long id);

    User getByIdOrThrow(long id);

    Page<User> findAllByIds(List<Long> ids, Pageable pageable);

    void deleteById(long id);

}
