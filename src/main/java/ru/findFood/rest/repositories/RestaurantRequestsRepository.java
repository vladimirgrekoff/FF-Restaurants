package ru.findFood.rest.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.findFood.rest.entities.RestaurantRequest;

import java.util.List;

@Repository
public interface RestaurantRequestsRepository extends JpaRepository<RestaurantRequest, Long> {
    List<RestaurantRequest> findAllByRestaurantTitle(String restaurant_title);
}
