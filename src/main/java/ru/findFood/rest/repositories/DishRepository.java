package ru.findFood.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.findFood.rest.entities.Dish;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

}
