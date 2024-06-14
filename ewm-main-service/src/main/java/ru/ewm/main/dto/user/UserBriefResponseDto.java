package ru.ewm.main.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserBriefResponseDto {

    private String name;

    private String email;

}
