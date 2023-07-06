package ru.findFood.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.findFood.rest.entities.RestaurantInfo;

@Repository
public interface RestaurantInfoRepository extends JpaRepository<RestaurantInfo, Long> {
}
