package ru.findFood.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.findFood.rest.entities.Restaurant;
import ru.findFood.rest.entities.RestaurantInfo;
import ru.findFood.rest.exceptions.ResourceNotFoundException;
import ru.findFood.rest.services.*;

import java.util.Arrays;
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
        Restaurant mockRestaurant = new Restaurant("Ресторация");
//        restaurantService.createNewRestaurant(mockRestaurant);///////////////////////////////
        Restaurant mockRestaurant1 = new Restaurant("Ресторация1");
//        restaurantService.createNewRestaurant(mockRestaurant1);/////////////////////////////
        Restaurant mockRestaurant2 = new Restaurant("Ресторация2");
//        restaurantService.createNewRestaurant(mockRestaurant2);/////////////////////////////
        List<Restaurant> correctRestaurantList = Arrays.asList(mockRestaurant, mockRestaurant1, mockRestaurant2);

        assertEquals(correctRestaurantList, restaurantService.findAll(null));
    }

    @Test
    public void findByIdTest(){
        Restaurant testRestaurant = new Restaurant("Ресторан");
//        restaurantService.createNewRestaurant(testRestaurant);/////////////////////////
        Long testRestaurantId = testRestaurant.getId();

        assertEquals(testRestaurantId, restaurantService.findById(testRestaurantId).getId());
    }

    @Test
    public void findByTitleTest(){
        Restaurant testRestaurant2 = new Restaurant("Ресторан2");
//        restaurantService.createNewRestaurant(testRestaurant2);/////////////////////
        Long testRestaurant2Id = testRestaurant2.getId();
        Restaurant testRestaurant3 = new Restaurant("Ресторан3");
//        restaurantService.createNewRestaurant(testRestaurant3);////////////////////////
        Long testRestaurant3Id = testRestaurant3.getId();

        assertEquals(testRestaurant2Id, restaurantService.findByTitle("Ресторан2").getId());
        assertEquals(testRestaurant3Id, restaurantService.findByTitle("Ресторан3").getId());
    }

    @Test
    public void createNewRestaurantTest() {
        Restaurant testRestaurant4 = new Restaurant("Ресторан4");
//        restaurantService.createNewRestaurant(testRestaurant4);///////////////////////
        RestaurantInfo testRestaurantInfo4 = testRestaurant4.getRestaurantInfo();
        testRestaurantInfo4.setDescription("Описание");
        restaurantInfoService.updateRestaurantInfo(testRestaurantInfo4);
        testRestaurant4 = restaurantService.findByTitle("Ресторан4");

        assertEquals("Ресторан4", testRestaurant4.getTitle());
        assertEquals("Описание", testRestaurant4.getRestaurantInfo().getDescription());
    }

    @Test
    public void updateRestaurantTest() {
        Restaurant testRestaurant5 = new Restaurant("Ресторан5");
//        restaurantService.createNewRestaurant(testRestaurant5);///////////////////////
        testRestaurant5.setTitle("McDonalds");
        restaurantService.updateRestaurant(testRestaurant5);

        assertEquals("McDonalds", testRestaurant5.getTitle());
    }

    @Test
    public void deleteRestaurantByIdTest(){
        Restaurant testRestaurant6 = new Restaurant("Ресторан6");
//        restaurantService.createNewRestaurant(testRestaurant6);///////////////////////////
        Long testRestaurant6Id = testRestaurant6.getId();
        restaurantService.deleteRestaurantById(testRestaurant6Id);

        assertThrows(ResourceNotFoundException.class, () -> restaurantService.findById(testRestaurant6Id));
        assertThrows(ResourceNotFoundException.class, () -> restaurantInfoService.findByRestaurantId(testRestaurant6Id));
    }
}
