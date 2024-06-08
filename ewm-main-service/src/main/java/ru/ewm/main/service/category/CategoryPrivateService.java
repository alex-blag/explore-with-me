package ru.ewm.main.service.category;

import ru.ewm.main.model.Category;

public interface CategoryPrivateService {

    Category getByIdOrThrow(long id);

}
