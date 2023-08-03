package ru.findFood.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.findFood.rest.dtos.UpdateRequestItemDto;
import ru.findFood.rest.entities.*;
import ru.findFood.rest.services.*;

import java.math.BigDecimal;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class RestaurantRequestServiceTests {

    @Autowired
    private RestaurantRequestsService restaurantRequestsService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DishesService dishesService;

    @Autowired
    private MailBoxService mailBoxService;

    @Autowired
    private GroupDishService groupDishService;

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findRestaurantRequestsTest (){
        Restaurant restaurant = new Restaurant("Ресторан");
        restaurantService.createNewRestaurant(restaurant);
        Restaurant restaurant1 = new Restaurant("Ресторан1");
        restaurantService.createNewRestaurant(restaurant1);
        restaurantRequestsService.createRequest("Ресторан");
        restaurantRequestsService.createRequest("Ресторан1");
        restaurantRequestsService.createRequest("Ресторан1");
        restaurantRequestsService.createRequest("Ресторан1");

        List<RestaurantRequest> actualRequestList = restaurantRequestsService.findRestaurantRequests("Ресторан");
        List<RestaurantRequest> actualRequestList1 = restaurantRequestsService.findRestaurantRequests("Ресторан1");

        assertEquals(1, actualRequestList.size());
        assertEquals(3, actualRequestList1.size());

        restaurantService.deleteRestaurantById(restaurant.getId());
        restaurantService.deleteRestaurantById(restaurant1.getId());
    }

    @Test
    public void createRequestTest (){
        Restaurant restaurant = new Restaurant("Ресторан1");
        restaurantService.createNewRestaurant(restaurant);
        GroupDish groupDish = new GroupDish("Группа");
        groupDishService.createNewGroupDish(groupDish);
        Category category = new Category("Категория");
        categoryService.createNewCategory(category);
        Dish dish = new Dish("Салат из помидоров", restaurant, "Описание", BigDecimal.valueOf(100), 19, 0, 0, 5, false, false, groupDish, category);
        dishesService.createNewDish(dish);
        String restaurantTitle = restaurant.getTitle();
        mailBoxService.addToMailBox(restaurantTitle, dish.getId());
        RestaurantRequest actualRequest = restaurantRequestsService.createRequest(restaurantTitle);

        assertEquals(dish.getId(), actualRequest.getRestaurantRequestItems().get(0).getDishId());
        assertEquals(dish.getTitle(), actualRequest.getRestaurantRequestItems().get(0).getTitle());
        assertEquals(dish.getDescription(), actualRequest.getRestaurantRequestItems().get(0).getDescription());
        assertEquals(dish.getGroupDish().getTitle(), actualRequest.getRestaurantRequestItems().get(0).getGroupDishTitle());
        assertEquals(dish.getCalories(), actualRequest.getRestaurantRequestItems().get(0).getCalories());
        assertEquals(dish.getProteins(), actualRequest.getRestaurantRequestItems().get(0).getProteins());
        assertEquals(dish.getFats(), actualRequest.getRestaurantRequestItems().get(0).getFats());
        assertEquals(dish.getCarbohydrates(), actualRequest.getRestaurantRequestItems().get(0).getCarbohydrates());
        assertEquals(dish.getCalories(), actualRequest.getRestaurantRequestItems().get(0).getCalories());
        assertEquals(dish.getHealthy(), actualRequest.getRestaurantRequestItems().get(0).getHealthy());
        assertEquals(dish.getApproved(), actualRequest.getRestaurantRequestItems().get(0).getApproved());

        restaurantService.deleteRestaurantById(restaurant.getId());
        dishesService.deleteDishById(dish.getId());
        groupDishService.deleteGroupDishById(groupDish.getId());
        categoryService.deleteCategoryById(category.getId());
    }

    @Test
    public void updateRequestTest (){
        Restaurant restaurant = new Restaurant("Ресторан2");
        restaurantService.createNewRestaurant(restaurant);
        GroupDish groupDish = new GroupDish("Группа");
        groupDishService.createNewGroupDish(groupDish);
        Category category = new Category("Категория");
        categoryService.createNewCategory(category);
        Dish dish = new Dish("Салат из помидоров", restaurant, "Описание", BigDecimal.valueOf(100), 19, 0, 0, 5, false, false, groupDish, category);
        dishesService.createNewDish(dish);
        String restaurantTitle = restaurant.getTitle();
        mailBoxService.addToMailBox(restaurantTitle, dish.getId());
        RestaurantRequest initialRequest = restaurantRequestsService.createRequest(restaurantTitle);
        UpdateRequestItemDto updateRequestItemDto = new UpdateRequestItemDto();
        updateRequestItemDto.setId(initialRequest.getRestaurantRequestItems().get(0).getId());
        updateRequestItemDto.setRequestId(initialRequest.getId());
        updateRequestItemDto.setDishId(dish.getId());
        updateRequestItemDto.setDishApproved(true);
        updateRequestItemDto.setDishHealthy(true);
        updateRequestItemDto.setVerified(true);
        restaurantRequestsService.updateRequest(updateRequestItemDto);
        RestaurantRequest updatedRequest = restaurantRequestsService.findRestaurantRequests(restaurantTitle).get(0);
        RestaurantRequestItem restaurantRequestItem = restaurantRequestsService.findRestaurantRequests(restaurantTitle).get(0).getRestaurantRequestItems().get(0);

        assertEquals(initialRequest.getId(), updatedRequest.getId());
        assertEquals(initialRequest.getRestaurantTitle(), updatedRequest.getRestaurantTitle());
        assertEquals(dish.getId(), restaurantRequestItem.getDishId());
        assertTrue(restaurantRequestItem.getApproved());
        assertTrue(restaurantRequestItem.getHealthy());
        assertTrue(restaurantRequestItem.getVerified());

        restaurantService.deleteRestaurantById(restaurant.getId());
        dishesService.deleteDishById(dish.getId());
        groupDishService.deleteGroupDishById(groupDish.getId());
        categoryService.deleteCategoryById(category.getId());
    }
}
