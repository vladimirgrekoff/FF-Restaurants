package ru.findFood.rest.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.findFood.rest.entities.Restaurant;
import ru.findFood.rest.entities.RestaurantInfo;
import ru.findFood.rest.exceptions.ResourceAlreadyInUseException;
import ru.findFood.rest.exceptions.ResourceNotFoundException;
import ru.findFood.rest.repositories.RestaurantRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantInfoService restaurantInfoService;
    private final DishesService dishesService;


    public List<Restaurant> findAll(){
        return restaurantRepository.findAll();
    }

    public Restaurant findById(Long id){
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if(restaurant.isPresent()){
            Restaurant restaurantFound = restaurant.get();
            restaurantFound.setDishes(dishesService.findAllByRestaurantId(restaurantFound.getId()));
            return restaurantFound;
        } else {
            throw new ResourceNotFoundException("Ресторан с ID "+ id + " не найден");
        }
    }

    public Restaurant findByTitle(String title){
        Optional<Restaurant> restaurant = restaurantRepository.findByTitle(title);
        if(restaurant.isPresent()){
            return restaurant.get();
        } else {
            throw new ResourceNotFoundException("Ресторан с названием "+ title + " не найден");
        }
    }

    @Transactional
    public void createNewRestaurant(Restaurant restaurant) {
        if (restaurantRepository.findByTitle(restaurant.getTitle()).isPresent()) {
            throw new ResourceAlreadyInUseException("Название ресторана: '" + restaurant.getTitle() + "' уже используется");
//        } else if ((restaurant.getId() != null || restaurant.getId() != 0) && restaurantRepository.findById(restaurant.getId()).isPresent()) {
//            throw new ResourceAlreadyInUseException("Ресторан с ID: " + restaurant.getId() + " уже есть в БД. Воспользуйтесь методом правки данных.");
        }

        RestaurantInfo restaurantInfo = new RestaurantInfo();

        restaurantRepository.save(restaurant);
        restaurantInfo.setRestaurant(restaurant);
        restaurantInfoService.createNewRestaurantInfo(restaurantInfo);
        restaurant.setRestaurantInfo(restaurantInfo);
        restaurantRepository.save(restaurant);
    }

    @Transactional
    public void updateRestaurant(Restaurant restaurant) {
        if (restaurant.getId() != null || restaurant.getId() != 0) {
            restaurantRepository.findById(restaurant.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Ресторан с ID: " + restaurant.getId() + " не найден"));

            restaurantRepository.save(restaurant);
        } else {
            throw new ResourceNotFoundException("Ресторан с ID: " + restaurant.getId() + " не найден");
        }
    }

    public void deleteRestaurantById(Long id) {
        restaurantRepository.deleteById(id);
    }
}
