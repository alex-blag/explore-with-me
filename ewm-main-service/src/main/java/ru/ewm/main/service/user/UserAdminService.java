package ru.ewm.main.service.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.ewm.main.dto.user.UserCreateRequestDto;
import ru.ewm.main.model.User;

import java.util.List;

public interface UserAdminService {

    User save(UserCreateRequestDto userCreateRequestDto);

    Page<User> findAllByIds(List<Long> ids, Pageable pageable);

    void deleteById(long id);

}
