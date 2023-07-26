package ru.findFood.rest.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.findFood.rest.converters.DishConverter;
import ru.findFood.rest.dtos.DishDto;
import ru.findFood.rest.dtos.UpdateDishTimeRequest;
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
        if (dish.isPresent()) {
            return dish.get();
        } else {
            throw new ResourceNotFoundException("Блюдо с ID " + id + " не найдено");
        }
    }

    public Dish findByTitle(String title) {
        Optional<Dish> dish = dishesRepository.findByTitle(title);
        if (dish.isPresent()) {
            return dish.get();
        } else {
            throw new ResourceNotFoundException("Блюдо с названием " + title + " не найдено");
        }
    }

    @Transactional
    public Dish createNewDish(Dish dish) {
        return dishesRepository.save(dish);
    }


    @Transactional
    public Dish updateDish(Dish dish) {
        if (dish.getId() != null || dish.getId() != 0) {
            Dish dishFound = dishesRepository.findById(dish.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Блюдо с id: " + dish.getId() + " не найдено"));

            if (dishFound != null) {
                dishFound = dish;
                dishesRepository.save(dishFound);
            }
            return dishFound;
        } else {
            throw new ResourceNotFoundException("Блюдо с id: " + dish.getId() + " не найдено");
        }
    }

    public void deleteDishById(Long id) {
        dishesRepository.deleteById(id);
    }


    public void updateLastUsedTime(UpdateDishTimeRequest request) {
        List<Long> idList = request
                .map()
                .keySet()
                .stream()
                .toList();
        List<Dish> dishes = dishesRepository.findByIdIn(idList);
        if (dishes == null || dishes.isEmpty()) {
            throw new ResourceNotFoundException("Блюда с id: " + request.map().keySet() + " не найдены");
        }
        dishes.forEach(dish -> dish.setUsedLastTime(
                request.map().get(dish.getId())
        ));
        dishesRepository.saveAll(dishes);
    }

    public List<Dish> findByCategory(String category, Integer querySize) {
        Pageable pageable = PageRequest.of(0,querySize);
        List<Dish> dishes = dishesRepository.findByCategoryTitle(category, pageable);
        if (dishes == null || dishes.isEmpty()) {
            throw new ResourceNotFoundException("Блюда с category: " + category + " не найдены");
        }
        return dishes;
    }
}
