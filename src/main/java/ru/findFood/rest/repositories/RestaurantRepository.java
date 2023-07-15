package ru.findFood.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.findFood.rest.entities.Restaurant;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("select r from Restaurant r where r.title = ?1")
    Optional<Restaurant> findByTitle(String title);
}
