package ru.ewm.main.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.ewm.main.dto.user.UserCreateRequestDto;
import ru.ewm.main.dto.user.UserListResponseDto;
import ru.ewm.main.dto.user.UserResponseDto;
import ru.ewm.main.mapper.UserMapper;
import ru.ewm.main.model.User;
import ru.ewm.main.service.user.UserAdminService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping(path = "/admin/users")
@RequiredArgsConstructor
@Validated
public class UserAdminController {

    private final UserAdminService userAdminService;
    private final UserMapper userMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto createUser(
            @RequestBody @Valid UserCreateRequestDto userCreateRequestDto
    ) {
        User user = userAdminService.save(userCreateRequestDto);
        return userMapper.toUserResponseDto(user);
    }

    @GetMapping
    public UserListResponseDto getAllUsersByParams(
            @RequestParam(defaultValue = "") List<Long> userIds,
            @RequestParam(defaultValue = "0") @PositiveOrZero int page,
            @RequestParam(defaultValue = "10") @Positive int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users = userAdminService.findAllByIds(userIds, pageable);
        return userMapper.toUserListResponseDto(users.getContent(), users.getTotalElements());
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(
            @PathVariable long userId
    ) {
        userAdminService.deleteById(userId);
    }

}
