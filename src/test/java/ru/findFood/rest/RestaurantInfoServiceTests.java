package ru.findFood.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.findFood.rest.entities.Restaurant;
import ru.findFood.rest.entities.RestaurantInfo;
import ru.findFood.rest.services.RestaurantInfoService;
import ru.findFood.rest.services.RestaurantService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RestaurantInfoServiceTests {

    @Autowired
    private RestaurantInfoService restaurantInfoService;

    @Autowired
    private RestaurantService restaurantService;

  //  @Test
    public void findAllTest(){
        Restaurant mockRestaurant4 = new Restaurant("Ресторация4", LocalDateTime.now());
        restaurantService.createNewRestaurant(mockRestaurant4);
        Restaurant mockRestaurant5 = new Restaurant("Ресторация5", LocalDateTime.now());
        restaurantService.createNewRestaurant(mockRestaurant5);
        Restaurant mockRestaurant6 = new Restaurant("Ресторация6",  LocalDateTime.now());
        restaurantService.createNewRestaurant(mockRestaurant6);
        List<RestaurantInfo> correctAllInfoList = Arrays.asList(mockRestaurant4.getRestaurantInfo(), mockRestaurant5.getRestaurantInfo(), mockRestaurant6.getRestaurantInfo());

        assertEquals(correctAllInfoList, restaurantInfoService.findAll());
    }

    @Test
    public void findByIdTest(){
        Restaurant testRestaurant7 = new Restaurant("Ресторан7", LocalDateTime.now());
        restaurantService.createNewRestaurant(testRestaurant7);
        Restaurant testRestaurant8 = new Restaurant("Ресторан8",  LocalDateTime.now());
        restaurantService.createNewRestaurant(testRestaurant8);
        RestaurantInfo testRestaurant8Info = testRestaurant8.getRestaurantInfo();
        testRestaurant8Info.setDescription("Описание");
        restaurantInfoService.updateRestaurantInfo(testRestaurant8Info);
        Long testRestaurant8InfoId = testRestaurant8Info.getId();

        assertEquals("Описание", restaurantInfoService.findById(testRestaurant8InfoId).getDescription());
    }

    @Test
    public void findByRestaurantIdTest(){
        Restaurant testRestaurant9 = new Restaurant("Ресторан9", LocalDateTime.now());
        restaurantService.createNewRestaurant(testRestaurant9);
        Restaurant testRestaurant10 = new Restaurant("Ресторан10",  LocalDateTime.now());
        restaurantService.createNewRestaurant(testRestaurant10);
        Long testRestaurant10Id = testRestaurant10.getId();
        RestaurantInfo testRestaurant10Info = testRestaurant10.getRestaurantInfo();
        testRestaurant10Info.setDescription("Описание");
        restaurantInfoService.updateRestaurantInfo(testRestaurant10Info);

        assertEquals("Описание", restaurantInfoService.findByRestaurantId(testRestaurant10Id).getDescription());
    }

    @Test
    public void updateRestaurantInfoTest() {
        Restaurant testRestaurant11 = new Restaurant("Ресторан11", LocalDateTime.now());
        restaurantService.createNewRestaurant(testRestaurant11);
        RestaurantInfo testRestaurant11Info = testRestaurant11.getRestaurantInfo();
        testRestaurant11Info.setDescription("Описание");
        testRestaurant11Info.setCuisines("Европейская");
        testRestaurant11Info.setEmail("email@testRestaurant11.com");
        testRestaurant11Info.setAddress("Адрес");
        testRestaurant11Info.setPhoneNumber("89260008899");
        testRestaurant11Info.setOpenHours("24/7");
        restaurantInfoService.updateRestaurantInfo(testRestaurant11Info);

        assertEquals("Описание", testRestaurant11.getRestaurantInfo().getDescription());
        assertEquals("Описание", testRestaurant11Info.getDescription());
        assertEquals("Европейская", testRestaurant11.getRestaurantInfo().getCuisines());
        assertEquals("Европейская", testRestaurant11Info.getCuisines());
        assertEquals("email@testRestaurant11.com", testRestaurant11.getRestaurantInfo().getEmail());
        assertEquals("email@testRestaurant11.com", testRestaurant11Info.getEmail());
        assertEquals("Адрес", testRestaurant11.getRestaurantInfo().getAddress());
        assertEquals("Адрес", testRestaurant11Info.getAddress());
        assertEquals("89260008899", testRestaurant11.getRestaurantInfo().getPhoneNumber());
        assertEquals("89260008899", testRestaurant11Info.getPhoneNumber());
        assertEquals("24/7", testRestaurant11.getRestaurantInfo().getOpenHours());
        assertEquals("24/7", testRestaurant11Info.getOpenHours());
    }
}
