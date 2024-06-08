package ru.ewm.main.service.category.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ewm.main.model.Category;
import ru.ewm.main.service.category.CategoryPublicService;
import ru.ewm.main.service.category.CategoryService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryPublicServiceImpl implements CategoryPublicService {

    private final CategoryService categoryService;

    @Override
    public Category getByIdOrThrow(long id) {
        return categoryService.getByIdOrThrow(id);
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryService.findAll(pageable);
    }

}
