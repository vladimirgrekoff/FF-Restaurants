package ru.findFood.rest.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.findFood.rest.dtos.RestaurantDto;
import ru.findFood.rest.entities.Restaurant;

@Component
@RequiredArgsConstructor
public class RestaurantConverter {
    private final RestaurantInfoConverter restaurantInfoConverter;

    public Restaurant dtoToEntity (RestaurantDto restaurantDto){
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantDto.getId());
        restaurant.setTitle(restaurantDto.getTitle());
        restaurant.setRestaurantInfo(restaurantInfoConverter.dtoToEntity(restaurantDto.getRestaurantInfoDto()));
        restaurant.setCreatedAt(restaurantDto.getCreatedAt());
        restaurant.setUpdatedAt(restaurantDto.getUpdatedAt());
        return restaurant;
    }

    public RestaurantDto entityToDto (Restaurant restaurant){
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(restaurant.getId());
        restaurantDto.setTitle(restaurant.getTitle());
        restaurantDto.setRestaurantInfoDto(restaurantInfoConverter.entityToDto(restaurant.getRestaurantInfo()));
        restaurantDto.setCreatedAt(restaurant.getCreatedAt());
        restaurantDto.setUpdatedAt(restaurant.getUpdatedAt());
        return restaurantDto;
    }
}
