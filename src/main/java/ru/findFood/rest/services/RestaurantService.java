package ru.findFood.rest.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.findFood.rest.entities.Restaurant;
import ru.findFood.rest.exceptions.ResourceNotFoundException;
import ru.findFood.rest.repositories.RestaurantRepository;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    public Restaurant findById(Long id){
        return restaurantRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ресторан с таким ID не найден"));
    }
}
