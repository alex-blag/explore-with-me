package ru.ewm.main.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.ewm.main.dto.category.CategoryCreateRequestDto;
import ru.ewm.main.dto.category.CategoryListResponseDto;
import ru.ewm.main.dto.category.CategoryResponseDto;
import ru.ewm.main.dto.category.CategoryUpdateRequestDto;
import ru.ewm.main.model.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    Category toCategory(CategoryCreateRequestDto categoryCreateRequestDto);

    CategoryResponseDto toCategoryResponseDto(Category category);

    CategoryListResponseDto toCategoryListResponseDto(List<Category> categories, Long totalElements);

    @BeanMapping(
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "id", ignore = true)
    void updateCategory(CategoryUpdateRequestDto categoryUpdateRequestDto, @MappingTarget Category category);

}
