package ru.findFood.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.findFood.rest.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
