package ru.findFood.rest.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.findFood.rest.converters.DishConverter;
import ru.findFood.rest.dtos.DishDto;
import ru.findFood.rest.entities.Dish;
import ru.findFood.rest.exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DishesService {
    private final DishConverter dishConverter;

    public List<DishDto> findAll() {
//        List<DishDto> dishDtoList = new ArrayList<>();
//        List<Dish> dishList = dishesRepository.findAll();
//        for (Dish d: dishList) {
//            DishDto dishDto = dishConverter.entityToDto(d);
//            dishDtoList.add(dishDto);
//        }
//        return dishDtoList;
        return null;
    }

    public Optional<Dish> findById(Long id) {
//        return dishesRepository.findById(id);
        return null;
    }

    @Transactional
    public void createNewProduct(DishDto dishDto) {
//        Dish dish = new Dish();
//        dish.setTitle(dishDto.getTitle());
//        dish.setPrice(dishDto.getPrice());
//        dish.setGroupDish(groupDishService.findByTitle(dishDto.getGroupDishTitle()).orElseThrow(() -> new ResourceNotFoundException("Группа блюд с названием: " + dishDto.getGroupDishTitle() + " не найдена")));
//        dishesRepository.save(dish);
    }


    @Transactional
    public void update(DishDto dishDto) {
//        Dish dish = dishesRepository.findById(dishDto.getId()).orElseThrow(()-> new ResourceNotFoundException("Продукт отсутствует в списке, id: " + dishDto.getId()));
//        dish.setPrice(dishDto.getPrice());
//        dish.setTitle(dishDto.getTitle());
//        dishesRepository.save(dish);
    }

    public void deleteById(Long id) {
//        dishesRepository.deleteById(id);
    }
}
