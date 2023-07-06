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
import ru.findFood.rest.repositories.DishesRepository;
import ru.findFood.rest.repositories.GroupDishRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DishesService {
    private final DishConverter dishConverter;
    private final DishesRepository dishesRepository;
    private final GroupDishService groupDishService;

    public List<DishDto> findAll() {
        List<DishDto> dishDtoList = new ArrayList<>();
        List<Dish> dishList = dishesRepository.findAll();
        for (Dish d: dishList) {
            DishDto dishDto = dishConverter.entityToDto(d);
            dishDtoList.add(dishDto);
        }
        return dishDtoList;
    }

    public DishDto findById(Long id) {
        return dishConverter.entityToDto(dishesRepository.findById(id).get());
    }

    @Transactional
    public void createNewProduct(DishDto dishDto) {
        Dish dish = dishConverter.dtoToEntity(dishDto);
        dish.setGroupDish(groupDishService.findByTitle(dishDto.getGroupDishDto().getTitle()).orElseThrow(() -> new ResourceNotFoundException("Группа блюд с названием: " + dishDto.getGroupDishDto().getTitle() + " не найдена")));
        dishesRepository.save(dish);
    }


    @Transactional
    public void update(DishDto dishDto) {
        Dish dish = dishesRepository.findById(dishDto.getId()).orElseThrow(()-> new ResourceNotFoundException("Продукт отсутствует в списке, id: " + dishDto.getId()));
        if (dish != null) {
            dish = dishConverter.dtoToEntity(dishDto);
            dishesRepository.save(dish);
        }
    }

    public void deleteById(Long id) {
        dishesRepository.deleteById(id);
    }
}
