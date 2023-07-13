package ru.findFood.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.findFood.rest.entities.*;
import ru.findFood.rest.services.*;

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
    private GroupDishService groupDishService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RestaurantService restaurantService;


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
    public void findAllByRestaurantIdTest(){
        Restaurant restaurant = new Restaurant();
        restaurant.setTitle("Ресторан1");
        this.restaurantService.createNewRestaurant(restaurant);
        Restaurant restaurant2 = new Restaurant();
        restaurant2.setTitle("Ресторан2");
        this.restaurantService.createNewRestaurant(restaurant2);

        GroupDish groupDish = new GroupDish();
        groupDish.setTitle("Фрукты");
        this.groupDishService.createNewGroupDish(groupDish);

        Category category = new Category();
        category.setTitle("Завтрак");
        this.categoryService.createNewCategory(category);

        Dish dish = new Dish("Апельсин", restaurant,"Марроканские апельсины", BigDecimal.valueOf(1.00),10,10,10,10,true,true, groupDish, category);
        this.dishesService.createNewDish(dish);
        Dish dish1 = new Dish("Помидор", restaurant2,"Сливовидные томаты", BigDecimal.valueOf(1.00),10,10,10,10,true,true, groupDish, category);
        this.dishesService.createNewDish(dish1);
        Dish dish2 = new Dish("Огурец", restaurant,"Короткоплодные огурцы", BigDecimal.valueOf(1.00),10,10,10,10,true,true, groupDish, category);
        this.dishesService.createNewDish(dish2);
        Dish dish3 = new Dish("Картофель", restaurant2,"Картофель отварной", BigDecimal.valueOf(1.00),10,10,10,10,true,true, groupDish, category);
        this.dishesService.createNewDish(dish3);

        List<Dish> correctDishesFromRestaurant = new ArrayList<>();
        correctDishesFromRestaurant.add(dish);
        correctDishesFromRestaurant.add(dish2);
        List<Dish> correctDishesFromRestaurant2 = new ArrayList<>();
        correctDishesFromRestaurant2.add(dish1);
        correctDishesFromRestaurant2.add(dish3);
        assertEquals(correctDishesFromRestaurant, this.dishesService.findAllByRestaurantId(1L));
        assertEquals(correctDishesFromRestaurant2, this.dishesService.findAllByRestaurantId(2L));
    }

    @Test
    public void findByIdTest(){
        Dish dish = new Dish("Помидор", 19, 0, 0, 5, LocalDateTime.now());
        this.dishesService.createNewDish(dish);
        dish = this.dishesService.findById(1L);
        assertEquals(1L, dish.getId());
    }

    @Test
    public void findByTitleTest(){
        Dish dish = new Dish("Помидор", 19, 0, 0, 5, LocalDateTime.now());
        this.dishesService.createNewDish(dish);
        Dish dish1 = new Dish("Картофельное пюре", 75, 1, 1, 15, LocalDateTime.now());
        this.dishesService.createNewDish(dish1);
        assertEquals(19, this.dishesService.findByTitle("Помидор").getCalories());
        assertEquals(75, this.dishesService.findByTitle("Картофельное пюре").getCalories());
    }

    @Test
    public void createNewDishTest() {
        GroupDish groupDish = new GroupDish();
        groupDish.setTitle("Фрукты");
        this.groupDishService.createNewGroupDish(groupDish);

        Category category = new Category();
        category.setTitle("Завтрак");
        this.categoryService.createNewCategory(category);


        Dish dish = new Dish("Апельсины",null,"Марроканские апельсины", BigDecimal.valueOf(100.0),10,10,10,10,true,true, groupDish, category);
        this.dishesService.createNewDish(dish);

        dish = this.dishesService.findByTitle("Апельсины");
        assertEquals("Апельсины", dish.getTitle());
        assertEquals("Марроканские апельсины", dish.getDescription());
        assertEquals("Фрукты", dish.getGroupDish().getTitle());
    }

    @Test
    public void updateDishTest() {
        Dish dish = new Dish("Помидор", 19, 0, 0, 5, LocalDateTime.now());
        this.dishesService.createNewDish(dish);
        dish.setDescription("Сливовидные томаты");
        dish.setTitle("Томаты");
        this.dishesService.updateDish(dish);
        assertEquals("Сливовидные томаты", this.dishesService.findById(1L).getDescription());
        assertEquals("Томаты", this.dishesService.findById(1L).getTitle());
    }

    @Test
    public void deleteDishByIdTest(){
        Dish dish = new Dish("Помидор", 19, 0, 0, 5, LocalDateTime.now());
        this.dishesService.createNewDish(dish);
        Dish dish1 = new Dish("Картофельное пюре", 75, 1, 1, 15, LocalDateTime.now());
        this.dishesService.createNewDish(dish1);
        Dish dish2 = new Dish("Огурец", 14, 0, 0, 4, LocalDateTime.now());
        this.dishesService.createNewDish(dish2);
        Dish dish3 = new Dish("Рыба", 160, 12, 10, 0, LocalDateTime.now());
        this.dishesService.createNewDish(dish3);

        this.dishesService.deleteDishById(2L);
        List<Dish> correctDishesWithDish1Deleted = new ArrayList<>();
        correctDishesWithDish1Deleted.add(dish);
        correctDishesWithDish1Deleted.add(dish2);
        correctDishesWithDish1Deleted.add(dish3);
        assertEquals(correctDishesWithDish1Deleted, this.dishesService.findAll());
    }
}
