package ru.ewm.main.dto.category;

import lombok.Data;

import java.util.List;

@Data
public class CategoryListResponseDto {

    private List<CategoryResponseDto> categories;

    private Long totalElements;

}
