package ru.findFood.rest.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.findFood.rest.dtos.DishDto;
import ru.findFood.rest.entities.Dish;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ListDishConverter {
    private final DishConverter dishConverter;

    public List<DishDto> entityToDto (List<Dish> dishList){
        List<DishDto> dishDtoList = new ArrayList<>();
        for (Dish d: dishList) {
            DishDto dishDto = dishConverter.entityToDto(d);
            dishDtoList.add(dishDto);
        }
        return dishDtoList;
    }

    public List<Dish> dtoToEntity (List<DishDto> dishDtoList){
        List<Dish> dishList = new ArrayList<>();
        for (DishDto d: dishDtoList) {
            Dish dish = dishConverter.dtoToEntity(d);
            dishList.add(dish);
        }
        return dishList;
    }
}