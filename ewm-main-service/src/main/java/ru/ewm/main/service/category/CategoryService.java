package ru.ewm.main.service.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.ewm.main.model.Category;

public interface CategoryService {

    Category save(Category category);

    Category getByIdOrThrow(long id);

    Page<Category> findAll(Pageable pageable);

    void deleteById(long id);

}
