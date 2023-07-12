package ru.findFood.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.findFood.rest.dtos.DishDto;
import ru.findFood.rest.dtos.GroupDishDto;
import org.springframework.boot.test.context.SpringBootTest;
import ru.findFood.rest.entities.Category;
import ru.findFood.rest.entities.Dish;
import ru.findFood.rest.entities.GroupDish;
import ru.findFood.rest.repositories.*;
import ru.findFood.rest.services.DishesService;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DishesServiceTests {
    //Может не сработать из-за генератора данных (javafaker.Faker),
    //генерируются слишко длинные значения для информации ресторана
    //повторный запуск может помочь
    @Autowired
    private DishesService dishesService;
    @Autowired
    private DishesRepository dishesRepository;

    @Autowired
    private GroupDishRepository groupDishRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void createNewDishesTest() {
        GroupDish groupDish = new GroupDish();
        groupDish.setId(8L);
        groupDish.setTitle("Фрукты");

        Category category = new Category();
        category.setId(1L);
        category.setTitle("Завтрак");


        Dish dish = new Dish(
                "Апельсины",
                null,
                "Марроканские апельсины",
                BigDecimal.valueOf(100.0),
                10,
                10,
                10,
                10,
                true,
                true,
                groupDish,
                category
        );

        this.dishesService.createNewDish(dish);

        dish = this.dishesRepository.findByTitle("Апельсины").get();
        assertTrue(dish.getTitle().equals("Апельсины"));
        assertTrue(dish.getDescription().equals("Марроканские апельсины"));
        assertTrue(dish.getGroupDish().getTitle().equals("Фрукты"));

    }
}
