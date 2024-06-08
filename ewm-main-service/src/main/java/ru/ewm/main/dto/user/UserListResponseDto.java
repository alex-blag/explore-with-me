package ru.ewm.main.dto.user;

import lombok.Data;
import ru.ewm.main.model.User;

import java.util.List;

@Data
public class UserListResponseDto {

    private List<User> users;

    private Long totalElements;

}
