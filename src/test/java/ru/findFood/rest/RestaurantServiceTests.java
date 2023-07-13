package ru.findFood.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.findFood.rest.entities.Restaurant;
import ru.findFood.rest.entities.RestaurantInfo;
import ru.findFood.rest.exceptions.ResourceNotFoundException;
import ru.findFood.rest.services.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RestaurantServiceTests {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private RestaurantInfoService restaurantInfoService;


    //@Test
    public void findAllTest(){
        Restaurant mockRestaurant = new Restaurant("Ресторация", LocalDateTime.now());
        restaurantService.createNewRestaurant(mockRestaurant);
        Restaurant mockRestaurant1 = new Restaurant("Ресторация1", LocalDateTime.now());
        restaurantService.createNewRestaurant(mockRestaurant1);
        Restaurant mockRestaurant2 = new Restaurant("Ресторация2",  LocalDateTime.now());
        restaurantService.createNewRestaurant(mockRestaurant2);

        List<Restaurant> correctRestaurantList = new ArrayList<>();
        correctRestaurantList.add(mockRestaurant);
        correctRestaurantList.add(mockRestaurant1);
        correctRestaurantList.add(mockRestaurant2);

        assertEquals(correctRestaurantList, restaurantService.findAll());
    }

    @Test
    public void findByIdTest(){
        Restaurant testRestaurant = new Restaurant("Ресторан", LocalDateTime.now());
        restaurantService.createNewRestaurant(testRestaurant);
        Long testRestaurantId = testRestaurant.getId();

        assertEquals(testRestaurantId, restaurantService.findById(testRestaurantId).getId());
    }

    @Test
    public void findByTitleTest(){
        Restaurant testRestaurant2 = new Restaurant("Ресторан2", LocalDateTime.now());
        restaurantService.createNewRestaurant(testRestaurant2);
        Long testRestaurant2Id = testRestaurant2.getId();
        Restaurant testRestaurant3 = new Restaurant("Ресторан3", LocalDateTime.now());
        restaurantService.createNewRestaurant(testRestaurant3);
        Long testRestaurant3Id = testRestaurant3.getId();

        assertEquals(testRestaurant2Id, restaurantService.findByTitle("Ресторан2").getId());
        assertEquals(testRestaurant3Id, restaurantService.findByTitle("Ресторан3").getId());
    }

    @Test
    public void createNewRestaurantTest() {
        Restaurant testRestaurant4 = new Restaurant("Ресторан4", LocalDateTime.now());
        restaurantService.createNewRestaurant(testRestaurant4);
        RestaurantInfo testRestaurantInfo4 = testRestaurant4.getRestaurantInfo();
        testRestaurantInfo4.setDescription("Описание");
        restaurantInfoService.updateRestaurantInfo(testRestaurantInfo4);

        testRestaurant4 = restaurantService.findByTitle("Ресторан4");
        assertEquals("Ресторан4", testRestaurant4.getTitle());
        assertEquals("Описание", testRestaurant4.getRestaurantInfo().getDescription());
    }

    @Test
    public void updateRestaurantTest() {
        Restaurant testRestaurant5 = new Restaurant("Ресторан5", LocalDateTime.now());
        restaurantService.createNewRestaurant(testRestaurant5);
        testRestaurant5.setTitle("McDonalds");
        testRestaurant5.setUpdatedAt(LocalDateTime.of(2023, 7, 13, 21, 7, 24, 59560700));
        restaurantService.updateRestaurant(testRestaurant5);

        assertEquals("McDonalds", testRestaurant5.getTitle());
        assertEquals(LocalDateTime.of(2023, 7, 13, 21, 7, 24, 59560700), testRestaurant5.getUpdatedAt());
    }

    @Test
    public void deleteRestaurantByIdTest(){
        Restaurant testRestaurant6 = new Restaurant("Ресторан6", LocalDateTime.now());
        restaurantService.createNewRestaurant(testRestaurant6);
        Long testRestaurant6Id = testRestaurant6.getId();

        restaurantService.deleteRestaurantById(testRestaurant6Id);
        assertThrows(ResourceNotFoundException.class, () -> restaurantService.findById(testRestaurant6Id));
        assertThrows(ResourceNotFoundException.class, () -> restaurantInfoService.findByRestaurantId(testRestaurant6Id));
    }
}
