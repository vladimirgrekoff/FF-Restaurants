package ru.findFood.rest.services;

import jakarta.xml.bind.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.findFood.rest.converters.RestaurantInfoConverter;
import ru.findFood.rest.entities.RestaurantInfo;
import ru.findFood.rest.exceptions.ResourceNotFoundException;
import ru.findFood.rest.repositories.RestaurantInfoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantInfoService {
    private final RestaurantInfoRepository restaurantInfoRepository;

    public List<RestaurantInfo> findAll(){
        return restaurantInfoRepository.findAll();
    }

    public RestaurantInfo findById(Long id) {
        Optional<RestaurantInfo> restaurantInfo = restaurantInfoRepository.findById(id);
        if (restaurantInfo.isPresent()) {
            return restaurantInfo.get();
        } else {
            throw new ResourceNotFoundException("Информация с ID " + id + " не найдена");
        }
    }

    public RestaurantInfo findByRestaurantId(Long id) {
        Optional<RestaurantInfo> restaurantInfo = restaurantInfoRepository.findByRestaurantId(id);
        if (restaurantInfo.isPresent()) {
            return restaurantInfo.get();
        } else {
            throw new ResourceNotFoundException("Информация о ресторане с таким ID - " + id + " не найдена");
        }
    }

    public void createNewRestaurantInfo(RestaurantInfo restaurantInfo) {
            if(restaurantInfo.getRestaurant() != null) {
            restaurantInfoRepository.save(restaurantInfo);
            } else {
                throw new ResourceNotFoundException("ID ресторана не указан");
            }
    }

    public void updateRestaurantInfo(RestaurantInfo restaurantInfo) {
        if (restaurantInfo.getId() != null || restaurantInfo.getId() != 0) {
            restaurantInfoRepository.findById(restaurantInfo.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Информация о ресторане с ID " +restaurantInfo.getId()+ " не найдена"));

            restaurantInfoRepository.save(restaurantInfo);
        } else {
            throw new ResourceNotFoundException("Информация о ресторане с ID " +restaurantInfo.getId()+ " не найдена");
        }
    }

    public RestaurantInfo findByEmail(String email) {
        Optional<RestaurantInfo> restaurantInfo = restaurantInfoRepository.findByEmail(email);
        if (restaurantInfo.isPresent()) {
            return restaurantInfo.get();
        } else {
            throw new ResourceNotFoundException("Информация с email " + email + " не найдена");
        }
    }
}
