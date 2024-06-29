package ru.ewm.main.controller.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.ewm.main.dto.category.CategoryCreateRequestDto;
import ru.ewm.main.dto.category.CategoryResponseDto;
import ru.ewm.main.dto.category.CategoryUpdateRequestDto;
import ru.ewm.main.mapper.CategoryMapper;
import ru.ewm.main.model.Category;
import ru.ewm.main.service.category.CategoryAdminService;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/admin/categories")
@RequiredArgsConstructor
public class CategoryAdminController {

    private final CategoryAdminService categoryAdminService;
    private final CategoryMapper categoryMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponseDto createCategory(
            @RequestBody @Valid CategoryCreateRequestDto categoryCreateRequestDto
    ) {
        Category category = categoryAdminService.save(categoryCreateRequestDto);
        return categoryMapper.toCategoryResponseDto(category);
    }

    @PatchMapping("/{categoryId}")
    public CategoryResponseDto updateCategoryById(
            @PathVariable long categoryId,
            @RequestBody @Valid CategoryUpdateRequestDto categoryUpdateRequestDto
    ) {
        Category category = categoryAdminService.updateById(categoryId, categoryUpdateRequestDto);
        return categoryMapper.toCategoryResponseDto(category);
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategoryById(
            @PathVariable long categoryId
    ) {
        categoryAdminService.deleteById(categoryId);
    }

}
