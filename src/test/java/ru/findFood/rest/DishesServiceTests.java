package ru.findFood.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.findFood.rest.entities.Category;
import ru.findFood.rest.entities.Dish;
import ru.findFood.rest.entities.GroupDish;
import ru.findFood.rest.repositories.*;
import ru.findFood.rest.services.CategoryService;
import ru.findFood.rest.services.DishesService;
import ru.findFood.rest.services.GroupDishService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class DishesServiceTests {

    @Autowired
    private DishesService dishesService;
    @Autowired
    private DishesRepository dishesRepository;

    @Autowired
    private GroupDishService groupDishService;

    @Autowired
    private CategoryService categoryService;


    @Test
    public void findAllTest(){
        Dish dish = new Dish("Помидор", 19, 0, 0, 5, LocalDateTime.now());
        this.dishesService.createNewDish(dish);
        Dish dish1 = new Dish("Огурец", 14, 0, 0, 4, LocalDateTime.now());
        this.dishesService.createNewDish(dish1);
        Dish dish2 = new Dish("Мясо", 200, 10, 17, 1, LocalDateTime.now());
        this.dishesService.createNewDish(dish2);
        List<Dish> allCorrectDishes = new ArrayList<>();
        allCorrectDishes.add(dish);
        allCorrectDishes.add(dish1);
        allCorrectDishes.add(dish2);
        List<Dish> allDishes = this.dishesService.findAll();
        assertEquals(allCorrectDishes, allDishes);
    }

    @Test
    public void findByIdTest(){

        Dish dish = new Dish("Помидор", 19, 0, 0, 5, LocalDateTime.now());
        this.dishesService.createNewDish(dish);
        dish = this.dishesService.findById(1L);
        assertEquals(1L, dish.getId());

    }
    @Test
    public void createNewDishTest() {
        GroupDish groupDish = new GroupDish();
        groupDish.setTitle("Фрукты");
        this.groupDishService.createNewGroupDish(groupDish);

        Category category = new Category();
        category.setTitle("Завтрак");
        this.categoryService.createNewCategory(category);


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
        assertEquals("Апельсины", dish.getTitle());
        assertEquals("Марроканские апельсины", dish.getDescription());
        assertEquals("Фрукты", dish.getGroupDish().getTitle());

    }





}
