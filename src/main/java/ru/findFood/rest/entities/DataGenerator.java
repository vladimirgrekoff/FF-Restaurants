package ru.findFood.rest.entities;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.findFood.rest.repositories.DishesRepository;
import java.time.LocalDateTime;

@Component
public class DataGenerator {

       @Autowired
       private DishesRepository dishesRepository;

       //Запускаем это класс, когда нужно заполнить базу сгенерированными данными
        //@EventListener(ApplicationReadyEvent.class)
        public void generateDishes() {
            Faker faker = new Faker();
            for (int i = 0; i < 100; i++) {
                Dish dish = new Dish(faker.food().dish(), faker.number().randomNumber(1, false), (int) faker.number().randomNumber(3, false), (int) faker.number().randomNumber(1, false), (int) faker.number().randomNumber(2, false), (int) faker.number().randomNumber(2, false), LocalDateTime.now());
                dishesRepository.save(dish);
            }
        }
    }

