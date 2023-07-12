package ru.findFood.rest.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.findFood.rest.dtos.RestaurantDto;
import ru.findFood.rest.dtos.newRestaurantDto;
import ru.findFood.rest.entities.Restaurant;
import ru.findFood.rest.services.RestaurantInfoService;
import ru.findFood.rest.services.RestaurantService;

@Component
@RequiredArgsConstructor
public class RestaurantConverter {
    private final RestaurantService restaurantService;
    private final RestaurantInfoService restaurantInfoService;

    public Restaurant dtoToEntity (RestaurantDto restaurantDto){
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantDto.getId());
        restaurant.setTitle(restaurantDto.getTitle());
        restaurant.setRestaurantInfo(restaurantInfoService.findById(restaurantDto.getRestaurant_info_id()));
        restaurant.setCreatedAt(restaurantDto.getCreatedAt());
        restaurant.setUpdatedAt(restaurantDto.getUpdatedAt());
        return restaurant;
    }

    public RestaurantDto entityToDto (Restaurant restaurant){
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(restaurant.getId());
        restaurantDto.setTitle(restaurant.getTitle());
        restaurantDto.setRestaurant_info_id(restaurant.getRestaurantInfo().getId());
        restaurantDto.setCreatedAt(restaurant.getCreatedAt());
        restaurantDto.setUpdatedAt(restaurant.getUpdatedAt());
        return restaurantDto;
    }

    public Restaurant dtoToEntity (newRestaurantDto newRestaurantDto){
        Restaurant restaurant = new Restaurant();
        restaurant.setTitle(newRestaurantDto.getTitle());
        restaurant.setCreatedAt(newRestaurantDto.getCreatedAt());
        restaurant.setUpdatedAt(newRestaurantDto.getUpdatedAt());
        return restaurant;
    }
}
