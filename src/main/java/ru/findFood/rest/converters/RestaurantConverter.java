package ru.findFood.rest.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.findFood.rest.dtos.NewRestaurantDetailsDto;
import ru.findFood.rest.dtos.RestaurantDto;
import ru.findFood.rest.dtos.NewRestaurantDto;
import ru.findFood.rest.entities.Restaurant;
import ru.findFood.rest.services.RestaurantInfoService;

@Component
@RequiredArgsConstructor
public class RestaurantConverter {
    private final ListDishConverter listDishConverter;
    private final RestaurantInfoService restaurantInfoService;

    public Restaurant dtoToEntity (RestaurantDto restaurantDto){
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantDto.getId());
        restaurant.setTitle(restaurantDto.getTitle());
        restaurant.setRestaurantInfo(restaurantInfoService.findById(restaurantDto.getRestaurant_info_id()));
        if((restaurantDto.getDishes()) != null){
            restaurant.setDishes(listDishConverter.dtoToEntity(restaurantDto.getDishes()));
        }
        restaurant.setCreatedAt(restaurantDto.getCreatedAt());
        restaurant.setUpdatedAt(restaurantDto.getUpdatedAt());
        return restaurant;
    }

    public RestaurantDto entityToDto (Restaurant restaurant){
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(restaurant.getId());
        restaurantDto.setTitle(restaurant.getTitle());
        restaurantDto.setRestaurant_info_id(restaurant.getRestaurantInfo().getId());
        if((restaurant.getDishes()) != null) {
            restaurantDto.setDishes(listDishConverter.entityToDto(restaurant.getDishes()));
        }
        restaurantDto.setCreatedAt(restaurant.getCreatedAt());
        restaurantDto.setUpdatedAt(restaurant.getUpdatedAt());
        return restaurantDto;
    }


    public NewRestaurantDetailsDto newEntityToDto (Restaurant restaurant){
        NewRestaurantDetailsDto newRestDetailsDto = new NewRestaurantDetailsDto();
        newRestDetailsDto.setId(restaurant.getId());
        newRestDetailsDto.setTitle(restaurant.getTitle());
        newRestDetailsDto.setRestaurant_info_id(restaurant.getRestaurantInfo().getId());
        return newRestDetailsDto;
    }

}
