package ru.findFood.rest.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.findFood.rest.dtos.DishDto;
import ru.findFood.rest.entities.Dish;
import ru.findFood.rest.services.CategoryService;
import ru.findFood.rest.services.GroupDishService;
import ru.findFood.rest.services.RestaurantService;


@Component
@RequiredArgsConstructor
public class DishConverter {
    private final GroupDishService groupDishService;
    private final CategoryService categoryService;
    private final RestaurantService restaurantService;


    public Dish dtoToEntity(DishDto dishDto) {
        Dish dish = new Dish();
        dish.setId(dishDto.getId());
        dish.setTitle(dishDto.getTitle());
        dish.setRestaurant(restaurantService.findByTitle(dishDto.getRestaurantTitle()));
        dish.setDescription(dishDto.getDescription());
        dish.setPrice(dishDto.getPrice());
//        dish.setImage(dishDto.getImage());
        dish.setCalories(dishDto.getCalories());
        dish.setProteins(dishDto.getProteins());
        dish.setFats(dishDto.getFats());
        dish.setCarbohydrates(dishDto.getCarbohydrates());
        dish.setHealthy(dishDto.getHealthy());
        dish.setApproved(dishDto.getApproved());
        dish.setGroupDish(groupDishService.findByTitle(dishDto.getGroupDishTitle()));
        dish.setCategory(categoryService.findByTitle(dishDto.getCategoryTitle()));
        dish.setCreatedAt(dishDto.getCreatedAt());
        dish.setUpdatedAt(dishDto.getUpdatedAt());
        return dish;
    }

    public DishDto entityToDto(Dish d) {
        DishDto dishDto = new DishDto();
        dishDto.setId(d.getId());
        dishDto.setTitle(d.getTitle());
        dishDto.setRestaurantTitle(d.getRestaurant().getTitle());
        dishDto.setDescription(d.getDescription());
        dishDto.setPrice(d.getPrice());
//        dishDto.setImage(d.getImage());
        dishDto.setCalories(d.getCalories());
        dishDto.setProteins(d.getProteins());
        dishDto.setFats(d.getFats());
        dishDto.setCarbohydrates(d.getCarbohydrates());
        dishDto.setHealthy(d.getHealthy());
        dishDto.setApproved(d.getApproved());
        dishDto.setGroupDishTitle(d.getGroupDish().getTitle());
        dishDto.setCategoryTitle(d.getCategory().getTitle());
        dishDto.setCreatedAt(d.getCreatedAt());
        dishDto.setUpdatedAt(d.getUpdatedAt());
        return dishDto;
    }

}
