package ru.findFood.rest.services;

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
    private final RestaurantInfoConverter restaurantInfoConverter;

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

    public RestaurantInfo findByRestaurantId(Long id){
        Optional<RestaurantInfo> restaurantInfo = restaurantInfoRepository.findByRestaurantId(id);
        if (restaurantInfo.isPresent()) {
            return restaurantInfo.get();
        } else {
            throw new ResourceNotFoundException("Информация о ресторане с таким ID - " + id + " не найдена");
        }
//        return restaurantInfoRepository.findByRestaurantId(id).orElseThrow(()-> new ResourceNotFoundException("Информация о ресторане с таким ID - " + id + " не найдена")));
    }

    public void createNewRestaurantInfo(RestaurantInfo restaurantInfo){
        restaurantInfoRepository.save(restaurantInfo);
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
}
