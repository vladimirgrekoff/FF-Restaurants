package ru.findFood.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.findFood.rest.entities.GroupDish;

import java.util.Optional;

public interface GroupDishRepository extends JpaRepository<GroupDish, Long> {
    @Query("select c from Category c where c.title = ?1")
    Optional<GroupDish> findByTitle(String title);
}
