package ru.findFood.rest.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.findFood.rest.dtos.DishDto;
import ru.findFood.rest.dtos.MenuDishDTO;
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
        dish.setRestaurant(restaurantService.findByTitle(dishDto.getRestaurant_title()));
        dish.setDescription(dishDto.getDescription());
        dish.setPrice(dishDto.getPrice());
        dish.setCalories(dishDto.getCalories());
        dish.setProteins(dishDto.getProteins());
        dish.setFats(dishDto.getFats());
        dish.setCarbohydrates(dishDto.getCarbohydrates());
        dish.setHealthy(dishDto.getHealthy());
        dish.setApproved(dishDto.getApproved());
        dish.setGroupDish(groupDishService.findByTitle(dishDto.getGroup_dish_title()));
        dish.setCategory(categoryService.findByTitle(dishDto.getCategory_title()));
        dish.setNutritionist_lastname(dishDto.getNutritionist_lastname());
        dish.setCreatedAt(dishDto.getCreatedAt());
        dish.setUpdatedAt(dishDto.getUpdatedAt());
        return dish;
    }

    public DishDto entityToDto(Dish d) {
        DishDto dishDto = new DishDto();
        dishDto.setId(d.getId());
        dishDto.setTitle(d.getTitle());
        dishDto.setRestaurant_title(d.getRestaurant().getTitle());
        dishDto.setDescription(d.getDescription());
        dishDto.setPrice(d.getPrice());
        dishDto.setCalories(d.getCalories());
        dishDto.setProteins(d.getProteins());
        dishDto.setFats(d.getFats());
        dishDto.setCarbohydrates(d.getCarbohydrates());
        dishDto.setHealthy(d.getHealthy());
        dishDto.setApproved(d.getApproved());
        dishDto.setGroup_dish_title(d.getGroupDish().getTitle());
        dishDto.setCategory_title(d.getCategory().getTitle());
        dishDto.setNutritionist_lastname(d.getNutritionist_lastname());
        dishDto.setCreatedAt(d.getCreatedAt());
        dishDto.setUpdatedAt(d.getUpdatedAt());
        return dishDto;
    }

    public MenuDishDTO entityToMenuDishDto(Dish dish) {
        return new MenuDishDTO(
                dish.getId(),
                dish.getTitle(),
                dish.getHealthy(),
                dish.getRestaurant().getTitle(),
                dish.getDescription(),
                dish.getPrice(),
                dish.getCalories(),
                dish.getProteins(),
                dish.getFats(),
                dish.getCarbohydrates(),
                dish.getApproved(),
                dish.getCategory().getTitle()
        );
    }
}
