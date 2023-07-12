package ru.findFood.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.findFood.rest.entities.RestaurantInfo;

import java.util.Optional;

@Repository
public interface RestaurantInfoRepository extends JpaRepository<RestaurantInfo, Long> {

//    @Query("select ri from RestaurantInfo ri where ri.restaurant.id = :requestedId")
//    Optional<RestaurantInfo> findByRestaurantId(@Param("requestedId")Long requestedId);
    Optional<RestaurantInfo> findByRestaurantId(Long restaurant_id);
}
