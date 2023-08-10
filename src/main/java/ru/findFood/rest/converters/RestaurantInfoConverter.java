package ru.findFood.rest.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.findFood.rest.dtos.RestaurantInfoDto;
import ru.findFood.rest.entities.RestaurantInfo;
import ru.findFood.rest.exceptions.ResourceNotFoundException;
import ru.findFood.rest.repositories.RestaurantRepository;

@Component
@RequiredArgsConstructor
public class RestaurantInfoConverter {


    public RestaurantInfo dtoToEntity (RestaurantInfoDto restaurantInfoDto){
        RestaurantInfo restaurantInfo = new RestaurantInfo();
        restaurantInfo.setId(restaurantInfoDto.getId());
        restaurantInfo.setDescription(restaurantInfoDto.getDescription());
        restaurantInfo.setCuisines(restaurantInfoDto.getCuisines());
        restaurantInfo.setAddress(restaurantInfoDto.getAddress());
        restaurantInfo.setPhoneNumber(restaurantInfoDto.getPhoneNumber());
        restaurantInfo.setEmail(restaurantInfoDto.getEmail());
        restaurantInfo.setOpenHours(restaurantInfoDto.getOpenHours());
        restaurantInfo.setCreatedAt(restaurantInfoDto.getCreatedAt());
        restaurantInfo.setUpdatedAt(restaurantInfoDto.getUpdatedAt());
        return restaurantInfo;
    }

    public RestaurantInfoDto entityToDto (RestaurantInfo restaurantInfo){
        RestaurantInfoDto restaurantInfoDto = new RestaurantInfoDto();
        restaurantInfoDto.setId(restaurantInfo.getId());
        restaurantInfoDto.setRestaurantId(restaurantInfo.getRestaurant().getId());
        restaurantInfoDto.setDescription(restaurantInfo.getDescription());
        restaurantInfoDto.setCuisines(restaurantInfo.getCuisines());
        restaurantInfoDto.setAddress(restaurantInfo.getAddress());
        restaurantInfoDto.setPhoneNumber(restaurantInfo.getPhoneNumber());
        restaurantInfoDto.setEmail(restaurantInfo.getEmail());
        restaurantInfoDto.setOpenHours(restaurantInfo.getOpenHours());
        restaurantInfoDto.setCreatedAt(restaurantInfo.getCreatedAt());
        restaurantInfoDto.setUpdatedAt(restaurantInfo.getUpdatedAt());
        return restaurantInfoDto;
    }
}
