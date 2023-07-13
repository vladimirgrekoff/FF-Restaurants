package ru.findFood.rest.utils;

import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.findFood.rest.entities.*;
import ru.findFood.rest.repositories.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

//Запускаем этот класс, когда нужно заполнить базу сгенерированными данными. Убираем комментарий с @Component
//@Component
public class DataGenerator {

    @Autowired
    private DishesRepository dishesRepository;
    @Autowired
    private GroupDishRepository groupDishRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantInfoRepository restaurantInfoRepository;


    @Transactional
    @PostConstruct
    public void generateData() {
        Faker faker = new Faker();

        //Создаем рестораны и инфо
        Restaurant restaurant1 = new Restaurant("Диетолог",  LocalDateTime.now());
        restaurantRepository.save(restaurant1);
        RestaurantInfo restaurantInfo1 = new RestaurantInfo(restaurant1, faker.company().industry(), faker.backToTheFuture().character(), faker.address().streetAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress(), faker.job().seniority(), LocalDateTime.now());
        restaurantInfoRepository.save(restaurantInfo1);
        restaurant1.setRestaurantInfo(restaurantInfo1);
        restaurantRepository.save(restaurant1);
        Restaurant restaurant2 = new Restaurant("Пиццерия",  LocalDateTime.now());
        restaurantRepository.save(restaurant2);
        RestaurantInfo restaurantInfo2 = new RestaurantInfo(restaurant2, faker.company().industry(), faker.backToTheFuture().character(), faker.address().streetAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress(), faker.job().seniority(), LocalDateTime.now());
        restaurantInfoRepository.save(restaurantInfo2);
        restaurant2.setRestaurantInfo(restaurantInfo2);
        restaurantRepository.save(restaurant2);
        Restaurant restaurant3 = new Restaurant("Стейкхаус",  LocalDateTime.now());
        restaurantRepository.save(restaurant3);
        RestaurantInfo restaurantInfo3 = new RestaurantInfo(restaurant3, faker.company().industry(), faker.backToTheFuture().character(), faker.address().streetAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress(), faker.job().seniority(), LocalDateTime.now());
        restaurantInfoRepository.save(restaurantInfo3);
        restaurant3.setRestaurantInfo(restaurantInfo3);
        restaurantRepository.save(restaurant3);
        Restaurant restaurant4 = new Restaurant("Бургерная",  LocalDateTime.now());
        restaurantRepository.save(restaurant4);
        RestaurantInfo restaurantInfo4 = new RestaurantInfo(restaurant4, faker.company().industry(), faker.backToTheFuture().character(), faker.address().streetAddress(), faker.phoneNumber().phoneNumber(), faker.internet().emailAddress(), faker.job().seniority(), LocalDateTime.now());
        restaurantInfoRepository.save(restaurantInfo4);
        restaurant4.setRestaurantInfo(restaurantInfo4);
        restaurantRepository.save(restaurant4);

        //Создаем категории
        Category breakfast = new Category("Завтрак", LocalDateTime.now());
        categoryRepository.save(breakfast);
        Category lunch = new Category("Обед", LocalDateTime.now());
        categoryRepository.save(lunch);
        Category dinner = new Category("Ужин", LocalDateTime.now());
        categoryRepository.save(dinner);
        Category snack = new Category("Перекус", LocalDateTime.now());
        categoryRepository.save(snack);

        //Создаем виды блюд
        GroupDish dessert = new GroupDish("Десерт", LocalDateTime.now());
        groupDishRepository.save(dessert);
        GroupDish drink = new GroupDish("Напиток", LocalDateTime.now());
        groupDishRepository.save(drink);
        GroupDish appetizer = new GroupDish("Закуска", LocalDateTime.now());
        groupDishRepository.save(appetizer);
        GroupDish salad = new GroupDish("Салат", LocalDateTime.now());
        groupDishRepository.save(salad);
        GroupDish soup = new GroupDish("Суп", LocalDateTime.now());
        groupDishRepository.save(soup);
        GroupDish mainCourse = new GroupDish("Второе", LocalDateTime.now());
        groupDishRepository.save(mainCourse);
        GroupDish garnish = new GroupDish("Гарнир", LocalDateTime.now());
        groupDishRepository.save(garnish);
        GroupDish fruits = new GroupDish("Фрукты", LocalDateTime.now());
        groupDishRepository.save(fruits);
        GroupDish vegetables = new GroupDish("Овощи", LocalDateTime.now());
        groupDishRepository.save(vegetables);

        //Заполняем базу блюд. Меняем число итераций на нужное в базе количество строк
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Dish dish = new Dish(faker.food().dish(), (int) faker.number().randomNumber(3, false), (int) faker.number().randomNumber(1, false), (int) faker.number().randomNumber(2, false), (int) faker.number().randomNumber(2, false), LocalDateTime.now());
            dish.setRestaurant(restaurantRepository.findById((long) random.nextInt(1, 5)).get());
            dish.setPrice(BigDecimal.valueOf(random.nextInt(79, 701)));;
            dish.setGroupDish(groupDishRepository.findById((long) random.nextInt(1, 10)).get());
            dish.setCategory(categoryRepository.findById((long) random.nextInt(1, 5)).get());
            dish.setDescription(faker.hobbit().character());
            dishesRepository.save(dish);
        }
    }
}


