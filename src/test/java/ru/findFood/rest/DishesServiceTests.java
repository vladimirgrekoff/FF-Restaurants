package ru.findFood.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.findFood.rest.entities.*;
import ru.findFood.rest.exceptions.ResourceNotFoundException;
import ru.findFood.rest.services.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


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


    //@Test
    public void findAllTest(){
        Dish mockDish = new Dish("Салат из помидоров", 19, 0, 0, 5, LocalDateTime.now());
        this.dishesService.createNewDish(mockDish);
        Dish mockDish1 = new Dish("Барбадосский резаный огурец", 14, 0, 0, 4, LocalDateTime.now());
        this.dishesService.createNewDish(mockDish1);
        Dish mockDish2 = new Dish("Жареное мясо", 200, 10, 17, 1, LocalDateTime.now());
        this.dishesService.createNewDish(mockDish2);
        List<Dish> allCorrectDishes = Arrays.asList(mockDish, mockDish1, mockDish2);
        List<Dish> allDishes = this.dishesService.findAll();

        assertEquals(allCorrectDishes, allDishes);
    }

    @Test
    public void findAllByRestaurantIdTest(){
        Restaurant testRestaurant01 = new Restaurant();
        testRestaurant01.setTitle("Ресторан01");
        this.restaurantService.createNewRestaurant(testRestaurant01);
        Long testRestaurant01Id = testRestaurant01.getId();
        Restaurant testRestaurant02 = new Restaurant();
        testRestaurant02.setTitle("Ресторан02");
        this.restaurantService.createNewRestaurant(testRestaurant02);
        Long testRestaurant02Id = testRestaurant02.getId();

        GroupDish testGroupDish = new GroupDish();
        testGroupDish.setTitle("Зелень");
        this.groupDishService.createNewGroupDish(testGroupDish);

        Category testCategory = new Category();
        testCategory.setTitle("Кофе-брейк");
        this.categoryService.createNewCategory(testCategory);

        Dish testDish = new Dish("Засахаренный апельсин", testRestaurant01,"Марроканские апельсины", BigDecimal.valueOf(1.00),10,10,10,10,true,true, testGroupDish, testCategory);
        this.dishesService.createNewDish(testDish);
        Dish testDish1 = new Dish("Помидоры с сыром", testRestaurant02,"Сливовидные томаты", BigDecimal.valueOf(1.00),10,10,10,10,true,true, testGroupDish, testCategory);
        this.dishesService.createNewDish(testDish1);
        Dish testDish2 = new Dish("Салат из огурцов", testRestaurant01,"Короткоплодные огурцы", BigDecimal.valueOf(1.00),10,10,10,10,true,true, testGroupDish, testCategory);
        this.dishesService.createNewDish(testDish2);
        Dish testDish3 = new Dish("Отварной картофель", testRestaurant02,"Картофель отварной", BigDecimal.valueOf(1.00),10,10,10,10,true,true, testGroupDish, testCategory);
        this.dishesService.createNewDish(testDish3);
        List<Dish> correctDishesFromRestaurant = Arrays.asList(testDish, testDish2);
        List<Dish> correctDishesFromRestaurant2 = Arrays.asList(testDish1, testDish3);

        assertEquals(correctDishesFromRestaurant, this.dishesService.findAllByRestaurantId(testRestaurant01Id));
        assertEquals(correctDishesFromRestaurant2, this.dishesService.findAllByRestaurantId(testRestaurant02Id));
    }

    @Test
    public void findByIdTest(){
        Dish testDish4 = new Dish("Помидор на мангале", 19, 0, 0, 5, LocalDateTime.now());
        this.dishesService.createNewDish(testDish4);
        Long testDish4Id = testDish4.getId();

        assertEquals(testDish4Id, this.dishesService.findById(testDish4Id).getId());
        assertEquals("Помидор на мангале", this.dishesService.findById(testDish4Id).getTitle());
    }

    @Test
    public void findByTitleTest(){
        Dish testDish5 = new Dish("Сырники", 250, 0, 0, 5, LocalDateTime.now());
        this.dishesService.createNewDish(testDish5);
        Dish testDish6 = new Dish("Картофельное пюре", 75, 1, 1, 15, LocalDateTime.now());
        this.dishesService.createNewDish(testDish6);

        assertEquals(250, this.dishesService.findByTitle("Сырники").getCalories());
        assertEquals(75, this.dishesService.findByTitle("Картофельное пюре").getCalories());
    }

    @Test
    public void createNewDishTest() {
        GroupDish testGroupDish1 = new GroupDish();
        testGroupDish1.setTitle("Мороженое");
        this.groupDishService.createNewGroupDish(testGroupDish1);

        Category testCategory1 = new Category();
        testCategory1.setTitle("Поздний ужин");
        this.categoryService.createNewCategory(testCategory1);

        Dish testDish7 = new Dish("Апельсиновое мороженое",null,"Марроканские апельсины", BigDecimal.valueOf(100.0),10,10,10,10,true,true, testGroupDish1, testCategory1);
        this.dishesService.createNewDish(testDish7);
        testDish7 = this.dishesService.findByTitle("Апельсиновое мороженое");

        assertEquals("Апельсиновое мороженое", testDish7.getTitle());
        assertEquals("Марроканские апельсины", testDish7.getDescription());
        assertEquals("Мороженое", testDish7.getGroupDish().getTitle());
    }

    @Test
    public void updateTest() {
        Dish testDish8 = new Dish("Помидорный соус", 19, 0, 0, 5, LocalDateTime.now());
        this.dishesService.createNewDish(testDish8);
        testDish8.setDescription("Сливовидные томаты");
        testDish8.setTitle("Томатный соус");
        this.dishesService.updateDish(testDish8);
        Long testDish8Id = testDish8.getId();

        assertEquals("Сливовидные томаты", this.dishesService.findById(testDish8Id).getDescription());
        assertEquals("Томатный соус", this.dishesService.findById(testDish8Id).getTitle());
    }

    @Test
    public void deleteByIdTest(){
        Dish testDish9 = new Dish("Помидор", 19, 0, 0, 5, LocalDateTime.now());
        this.dishesService.createNewDish(testDish9);
        Long testDish9Id = testDish9.getId();
        this.dishesService.deleteDishById(testDish9Id);

        assertThrows(ResourceNotFoundException.class, ()-> this.dishesService.findById(testDish9Id));
    }
}
