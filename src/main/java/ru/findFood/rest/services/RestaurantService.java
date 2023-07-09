package ru.findFood.rest.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.findFood.rest.converters.RestaurantConverter;
import ru.findFood.rest.dtos.RestaurantDto;
import ru.findFood.rest.entities.Restaurant;
import ru.findFood.rest.exceptions.ResourceNotFoundException;
import ru.findFood.rest.repositories.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantConverter restaurantConverter;

    public RestaurantDto findById(Long id){
        return restaurantConverter.entityToDto(restaurantRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ресторан с ID "+ id + " не найден")));
    }

    public RestaurantDto findByTitle(String title){
        return restaurantConverter.entityToDto(restaurantRepository.findByTitle(title).orElseThrow(() -> new ResourceNotFoundException("Ресторан с названием "+ title + " не найден")));
    }

    public List<RestaurantDto> findAll(){
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        List<RestaurantDto> restaurantDtoList = new ArrayList<>();
        for(Restaurant r: restaurantList){
            restaurantDtoList.add(restaurantConverter.entityToDto(r));
        }
        return restaurantDtoList;
    }

    public RestaurantDto createNewRestaurant(RestaurantDto restaurantDto) {
        Restaurant restaurant = restaurantConverter.dtoToEntity(restaurantDto);
        restaurantRepository.save(restaurant);
        restaurantDto.setId(restaurant.getId());
        return restaurantDto;
    }

    public RestaurantDto updateRestaurant(RestaurantDto restaurantDto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantDto.getId()).orElseThrow(()-> new ResourceNotFoundException("Ресторан с ID"+ restaurantDto.getId() + " не найден"));
        if(restaurant != null){
            restaurant = restaurantConverter.dtoToEntity(restaurantDto);
            restaurantRepository.save(restaurant);
        }
        return restaurantDto;
    }

    public void deleteRestaurantById(Long id) {
        restaurantRepository.deleteById(id);
    }
}
