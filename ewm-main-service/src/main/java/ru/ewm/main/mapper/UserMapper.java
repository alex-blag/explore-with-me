package ru.ewm.main.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ewm.main.dto.user.UserCreateRequestDto;
import ru.ewm.main.dto.user.UserListResponseDto;
import ru.ewm.main.dto.user.UserResponseDto;
import ru.ewm.main.model.User;

import java.util.List;

@Mapper
public interface UserMapper {

    UserResponseDto toUserResponseDto(User user);

    UserListResponseDto toUserListResponseDto(List<User> users, long totalElements);

    @Mapping(target = "id", ignore = true)
    User toUser(UserCreateRequestDto userCreateRequestDto);

}
