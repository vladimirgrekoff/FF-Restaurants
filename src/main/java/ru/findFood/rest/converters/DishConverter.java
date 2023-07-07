package ru.findFood.rest.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.findFood.rest.dtos.DishDto;
import ru.findFood.rest.entities.Dish;


@Component
@RequiredArgsConstructor
public class DishConverter {
    private final GroupDishDtoConverter groupDishDtoConverter;
    private final RestaurantConverter restaurantConverter;
    private final CategoryConverter categoryConverter;


    public Dish dtoToEntity(DishDto dishDto) {
        Dish dish = new Dish();
        dish.setId(dishDto.getId());
        dish.setTitle(dishDto.getTitle());
        dish.setHealthy(dishDto.getHealthy());
        dish.setRestaurant(restaurantConverter.dtoToEntity(dishDto.getRestaurantDto()));
        dish.setDescription(dishDto.getDescription());
        dish.setPrice(dishDto.getPrice());
        dish.setImage(dishDto.getImage());
        dish.setCalories(dishDto.getCalories());
        dish.setProteins(dishDto.getProteins());
        dish.setFats(dishDto.getFats());
        dish.setCarbohydrates(dishDto.getCarbohydrates());
        dish.setApproved(dishDto.getApproved());
        dish.setGroupDish(groupDishDtoConverter.dtoToEntity(dishDto.getGroupDishDto()));
        dish.setCategory(categoryConverter.dtoToEntity(dishDto.getCategoryDto()));
        dish.setCreatedAt(dishDto.getCreatedAt());
        dish.setUpdatedAt(dishDto.getUpdatedAt());
        return dish;
    }

    public DishDto entityToDto(Dish d) {
        DishDto dishDto = new DishDto();
        dishDto.setId(d.getId());
        dishDto.setTitle(d.getTitle());
        dishDto.setHealthy(d.getHealthy());
        dishDto.setRestaurantDto(restaurantConverter.entityToDto(d.getRestaurant()));
        dishDto.setDescription(d.getDescription());
        dishDto.setPrice(d.getPrice());
        dishDto.setImage(d.getImage());
        dishDto.setCalories(d.getCalories());
        dishDto.setProteins(d.getProteins());
        dishDto.setFats(d.getFats());
        dishDto.setCarbohydrates(d.getCarbohydrates());
        dishDto.setApproved(d.getApproved());
        dishDto.setGroupDishDto(groupDishDtoConverter.entityToDto(d.getGroupDish()));
        dishDto.setCategoryDto(categoryConverter.entityToDto(d.getCategory()));
        dishDto.setCreatedAt(d.getCreatedAt());
        dishDto.setUpdatedAt(d.getUpdatedAt());
        return dishDto;
    }

}
