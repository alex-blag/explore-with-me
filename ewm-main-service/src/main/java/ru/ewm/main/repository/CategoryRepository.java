package ru.ewm.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ewm.main.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
