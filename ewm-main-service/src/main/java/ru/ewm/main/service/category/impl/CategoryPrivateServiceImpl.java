package ru.ewm.main.service.category.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ewm.main.model.Category;
import ru.ewm.main.service.category.CategoryPrivateService;
import ru.ewm.main.service.category.CategoryService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryPrivateServiceImpl implements CategoryPrivateService {

    private final CategoryService categoryService;

    @Override
    public Category getByIdOrThrow(long id) {
        return categoryService.getByIdOrThrow(id);
    }

}
