package ru.ewm.main.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ewm.main.dto.category.CategoryListResponseDto;
import ru.ewm.main.dto.category.CategoryResponseDto;
import ru.ewm.main.mapper.CategoryMapper;
import ru.ewm.main.model.Category;
import ru.ewm.main.service.category.CategoryPublicService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@RestController
@RequestMapping(path = "/categories")
@RequiredArgsConstructor
@Validated
public class CategoryPublicController {

    private final CategoryPublicService categoryPublicService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public CategoryListResponseDto getAllCategoriesByParams(
            @RequestParam(defaultValue = "0") @PositiveOrZero int page,
            @RequestParam(defaultValue = "10") @Positive int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categories = categoryPublicService.findAll(pageable);
        return categoryMapper.toCategoryListResponseDto(categories.getContent(), categories.getTotalElements());
    }

    @GetMapping("/{categoryId}")
    public CategoryResponseDto getCategoryById(
            @PathVariable long categoryId
    ) {
        Category category = categoryPublicService.getByIdOrThrow(categoryId);
        return categoryMapper.toCategoryResponseDto(category);
    }

}
