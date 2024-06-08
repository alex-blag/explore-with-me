package ru.ewm.main.dto.category;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CategoryCreateRequestDto {

    @NotBlank
    @Size(max = 120)
    private String name;

}
