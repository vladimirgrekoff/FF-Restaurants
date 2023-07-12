package ru.findFood.rest.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.findFood.rest.dtos.RestaurantDto;
import ru.findFood.rest.dtos.shortRestaurantDto;
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

    public Restaurant dtoToEntity (shortRestaurantDto shortRestaurantDto){
        Restaurant restaurant = new Restaurant();
        restaurant.setTitle(shortRestaurantDto.getTitle());
        restaurant.setCreatedAt(shortRestaurantDto.getCreatedAt());
        restaurant.setUpdatedAt(shortRestaurantDto.getUpdatedAt());
        return restaurant;
    }
}
