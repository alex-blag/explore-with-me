package ru.ewm.main.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryListResponseDto {

    private List<CategoryResponseDto> categories;

    private Long totalElements;

}
