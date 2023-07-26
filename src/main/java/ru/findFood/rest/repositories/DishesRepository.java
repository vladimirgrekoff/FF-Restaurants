package ru.findFood.rest.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.findFood.rest.entities.Dish;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishesRepository extends JpaRepository<Dish, Long> {
//    @Query("select count(*) from Dish")
//    Long countDishes();

    //    @Query("select d from Dish d where d.title = ?1")
    Optional<Dish> findByTitle(String title);


    List<Dish> findAllByRestaurantId(Long restaurant_id);

    List<Dish> findByIdIn(List<Long> idList);

    @Query("select d from Dish d where d.category.title = ?1 order by d.usedLastTime")
    List<Dish> findByCategoryTitle(String category, Pageable pageable);
}
