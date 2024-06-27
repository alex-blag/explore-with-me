package ru.ewm.main.service.category.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ewm.main.dto.category.CategoryCreateRequestDto;
import ru.ewm.main.dto.category.CategoryUpdateRequestDto;
import ru.ewm.main.mapper.CategoryMapper;
import ru.ewm.main.model.Category;
import ru.ewm.main.service.category.CategoryAdminService;
import ru.ewm.main.service.category.CategoryService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryAdminServiceImpl implements CategoryAdminService {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public Category save(CategoryCreateRequestDto categoryCreateRequestDto) {
        Category category = categoryMapper.toCategory(categoryCreateRequestDto);
        return categoryService.save(category);
    }

    @Override
    @Transactional
    public Category updateById(long id, CategoryUpdateRequestDto categoryUpdateRequestDto) {
        Category category = getByIdOrThrow(id);
        categoryMapper.updateCategory(categoryUpdateRequestDto, category);
        return category;
    }

    @Override
    public Category getByIdOrThrow(long id) {
        return categoryService.getByIdOrThrow(id);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        categoryService.deleteById(id);
    }

}
