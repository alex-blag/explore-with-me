package ru.ewm.main.service.category;

import ru.ewm.main.dto.CategoryCreateRequestDto;
import ru.ewm.main.dto.CategoryUpdateRequestDto;
import ru.ewm.main.model.Category;

public interface CategoryAdminService {

    Category save(CategoryCreateRequestDto categoryCreateRequestDto);

    Category updateById(long id, CategoryUpdateRequestDto categoryUpdateRequestDto);

    Category getByIdOrThrow(long id);

    void deleteById(long id);

}
