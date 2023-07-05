package ru.findFood.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.findFood.rest.entities.GroupDish;

public interface GroupDishRepository extends JpaRepository<GroupDish, Long> {
}
