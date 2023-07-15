package ru.findFood.rest.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.findFood.rest.entities.Dish;
import ru.findFood.rest.exceptions.ResourceNotFoundException;
import ru.findFood.rest.repositories.DishesRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DishesService {
    private final DishesRepository dishesRepository;

    public List<Dish> findAll() {
        return dishesRepository.findAll();
    }

    public List<Dish> findAllByRestaurantId(Long restaurant_id) {
        return dishesRepository.findAllByRestaurantId(restaurant_id);
    }

    public Dish findById(Long id) {
        Optional<Dish> dish = dishesRepository.findById(id);
        if(dish.isPresent()){
            return dish.get();
        } else {
            throw new ResourceNotFoundException("Блюдо с ID " + id + " не найдено");
        }
    }

    public Dish findByTitle(String title) {
        Optional<Dish> dish = dishesRepository.findByTitle(title);
        if(dish.isPresent()){
            return dish.get();
        } else {
            throw new ResourceNotFoundException("Блюдо с названием " + title + " не найдено");
        }
    }

    @Transactional
    public void createNewDish(Dish dish) {
        dishesRepository.save(dish);
    }


    @Transactional
    public void updateDish(Dish dish) {
        if (dish.getId() != null || dish.getId() != 0) {
            Dish dishFound = dishesRepository.findById(dish.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Блюдо с id: " + dish.getId() + " не найдено"));

            if (dishFound != null) {
                dishesRepository.save(dish);
            }
        } else {
            throw new ResourceNotFoundException("Блюдо с id: " + dish.getId() + " не найдено");
        }
    }

    public void deleteDishById(Long id) {
        dishesRepository.deleteById(id);
    }
}
