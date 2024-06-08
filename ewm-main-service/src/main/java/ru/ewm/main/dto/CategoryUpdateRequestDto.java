package ru.ewm.main.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CategoryUpdateRequestDto {

    @NotBlank
    @Size(max = 120)
    private String name;

}
