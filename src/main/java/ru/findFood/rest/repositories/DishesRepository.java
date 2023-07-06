package ru.findFood.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.findFood.rest.entities.Dish;

import java.util.Optional;

@Repository
public interface DishesRepository extends JpaRepository<Dish, Long> {
/*    @Query("select count(*) from Dish")
    Long countDishes();

    @Query("select d from Dish d where d.title = ?1")
    Optional<Dish> findByTitle(String title);*/

}
