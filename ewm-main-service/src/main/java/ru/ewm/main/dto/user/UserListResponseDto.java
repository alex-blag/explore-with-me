package ru.ewm.main.dto.user;

import lombok.Data;

import java.util.List;

@Data
public class UserListResponseDto {

    private List<UserResponseDto> users;

    private Long totalElements;

}
