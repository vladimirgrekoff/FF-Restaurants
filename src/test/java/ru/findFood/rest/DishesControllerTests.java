package ru.findFood.rest;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import org.springframework.web.util.UriComponentsBuilder;
import ru.findFood.rest.dtos.DishDto;
import ru.findFood.rest.services.DishesService;
import ru.findFood.rest.utils.Constants;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DishesControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DishesService dishesService;


    @Test
    public void readAllDishesEndpointTest() {
        String url = "http://localhost:" + port + "/ff-restaurants/api/v1/dishes/all";
        ResponseEntity<List<DishDto>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        List<DishDto> dishes = responseEntity.getBody();
        HttpStatusCode statusCode = responseEntity.getStatusCode();

        assertEquals(200, statusCode.value());
        assertTrue("Response body is empty", dishes != null && !dishes.isEmpty());
        if (dishes != null) {
            for (DishDto dishDto : dishes) {
                assertTrue("Missing field: id", dishDto.getId() != null);
                assertTrue("Missing field: title", dishDto.getTitle() != null);
                assertTrue("Missing field: restaurantTitle", dishDto.getRestaurantTitle() != null);
                assertTrue("Missing field: calories", dishDto.getCalories() != null);
                assertTrue("Missing field: proteins", dishDto.getProteins() != null);
                assertTrue("Missing field: fats", dishDto.getFats() != null);
                assertTrue("Missing field: carbohydrates", dishDto.getCarbohydrates() != null);
                assertTrue("Missing field: groupDishTitle", dishDto.getGroupDishTitle() != null);
                assertTrue("Missing field: categoryTitle", dishDto.getCategoryTitle() != null);
                assertTrue("Missing field: createdAt", dishDto.getCreatedAt() != null);
            }
        }
        int actualCount = 0;
        if (dishes != null) {
            actualCount = dishes.size();
        }
        int expectedCount = Constants.INMEMORYDATABASEDISHESCOUNT;
        assertEquals(expectedCount, actualCount);
        assertEquals(expectedCount, actualCount);
    }

    @Test
    public void readAllDishesByRestaurantIdEndpointTest() {

        Long restaurantId = 1L;
        String url = "http://localhost:" + port + "/ff-restaurants/api/v1/dishes/restaurant/{id}";
        ResponseEntity<List<DishDto>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                },
                restaurantId
        );
        List<DishDto> dishes = responseEntity.getBody();
        HttpStatusCode statusCode = responseEntity.getStatusCode();

        assertEquals(200, statusCode.value());
        assertTrue("Response body is empty", dishes != null && !dishes.isEmpty());

        String expectedRestaurantTitle = null;
        if (dishes != null) {
            for (DishDto dishDto : dishes) {
                String restaurantTitle = dishDto.getRestaurantTitle();
                if (expectedRestaurantTitle == null) {
                    expectedRestaurantTitle = restaurantTitle;
                } else {
                    assertEquals(expectedRestaurantTitle, restaurantTitle);
                }
                assertTrue("Missing field: id", dishDto.getId() != null);
                assertTrue("Missing field: title", dishDto.getTitle() != null);
                assertTrue("Missing field: restaurantTitle", dishDto.getRestaurantTitle() != null);
                assertTrue("Missing field: calories", dishDto.getCalories() != null);
                assertTrue("Missing field: proteins", dishDto.getProteins() != null);
                assertTrue("Missing field: fats", dishDto.getFats() != null);
                assertTrue("Missing field: carbohydrates", dishDto.getCarbohydrates() != null);
                assertTrue("Missing field: groupDishTitle", dishDto.getGroupDishTitle() != null);
                assertTrue("Missing field: categoryTitle", dishDto.getCategoryTitle() != null);
                assertTrue("Missing field: createdAt", dishDto.getCreatedAt() != null);
            }
        }
    }

    @Test
    public void readDishByIdEndpointTest() {
        Long expectedDishId = 1L;
        String url = "http://localhost:" + port + "/ff-restaurants/api/v1/dishes/{id}";
        ResponseEntity<DishDto> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                },
                expectedDishId
        );
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        DishDto dishDto = responseEntity.getBody();

        assertEquals(200, statusCode.value());
        assertNotNull("Response body is empty", dishDto);
        if (dishDto != null) {
            Long actualDishId = dishDto.getId();
            assertEquals(expectedDishId, actualDishId);
            assertTrue("Missing field: id", dishDto.getId() != null);
            assertTrue("Missing field: title", dishDto.getTitle() != null);
            assertTrue("Missing field: restaurantTitle", dishDto.getRestaurantTitle() != null);
            assertTrue("Missing field: calories", dishDto.getCalories() != null);
            assertTrue("Missing field: proteins", dishDto.getProteins() != null);
            assertTrue("Missing field: fats", dishDto.getFats() != null);
            assertTrue("Missing field: carbohydrates", dishDto.getCarbohydrates() != null);
            assertTrue("Missing field: groupDishTitle", dishDto.getGroupDishTitle() != null);
            assertTrue("Missing field: categoryTitle", dishDto.getCategoryTitle() != null);
            assertTrue("Missing field: createdAt", dishDto.getCreatedAt() != null);
        }
    }

    @Test
    public void createNewDishEndpointTest() {
        Faker faker = new Faker();
        DishDto dishDto = new DishDto(faker.food().dish(), "Диетолог", 35, 6, 1, 0, "Суп", "Обед");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<DishDto> requestEntity = new HttpEntity<>(dishDto, headers);
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port)
                .path("/ff-restaurants/api/v1/dishes")
                .build()
                .toUri();
        ResponseEntity<DishDto> responseEntity = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                requestEntity,
                DishDto.class
        );
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        DishDto actualDishDto = responseEntity.getBody();

        assertEquals(HttpStatus.CREATED, statusCode);
        assertNotNull("Response body is empty", actualDishDto);
        if (actualDishDto != null) {
            assertTrue("Missing field: id", actualDishDto.getId() != null);
            assertTrue("Missing field: title", Objects.equals(actualDishDto.getTitle(), dishDto.getTitle()));
            assertTrue("Missing field: restaurantTitle", Objects.equals(actualDishDto.getRestaurantTitle(), dishDto.getRestaurantTitle()));
            assertTrue("Missing field: calories", Objects.equals(actualDishDto.getCalories(), dishDto.getCalories()));
            assertTrue("Missing field: proteins", Objects.equals(actualDishDto.getProteins(), dishDto.getProteins()));
            assertTrue("Missing field: fats", Objects.equals(actualDishDto.getFats(), dishDto.getFats()));
            assertTrue("Missing field: carbohydrates", Objects.equals(actualDishDto.getCarbohydrates(), dishDto.getCarbohydrates()));
            assertTrue("Missing field: groupDishTitle", Objects.equals(actualDishDto.getGroupDishTitle(), dishDto.getGroupDishTitle()));
            assertTrue("Missing field: categoryTitle", Objects.equals(actualDishDto.getCategoryTitle(), dishDto.getCategoryTitle()));
            assertTrue("Missing field: createdAt", actualDishDto.getCreatedAt() != null);
        }

        dishesService.deleteDishById(Objects.requireNonNull(responseEntity.getBody()).getId());
    }

    @Test
    public void updateDishEndpointTest() {
        DishDto dishDto1 = new DishDto("Название", "Диетолог", 72, 6, 4, 3, "Суп", "Ужин");
        HttpHeaders headers1 = new HttpHeaders();
        headers1.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<DishDto> requestEntity = new HttpEntity<>(dishDto1, headers1);
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port)
                .path("/ff-restaurants/api/v1/dishes")
                .build()
                .toUri();
        ResponseEntity<DishDto> responseEntity = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                requestEntity,
                DishDto.class
        );
        Long id = responseEntity.getBody().getId();
        LocalDateTime createdAt = responseEntity.getBody().getCreatedAt();
        dishDto1.setId(id);
        dishDto1.setCreatedAt(createdAt);
        dishDto1.setTitle("Жаркое");
        dishDto1.setRestaurantTitle("Стейкхаус");
        dishDto1.setCalories(280);
        dishDto1.setGroupDishTitle("Второе");
        dishDto1.setCategoryTitle("Обед");
        HttpHeaders headers2 = new HttpHeaders();
        headers2.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<DishDto> requestEntity1 = new HttpEntity<>(dishDto1, headers2);
        URI uri1 = URI.create("http://localhost:" + port + "/ff-restaurants/api/v1/dishes");
        ResponseEntity<Void> responseEntity1 = restTemplate.exchange(
                uri1,
                HttpMethod.PUT,
                requestEntity1,
                Void.class
        );
        HttpStatusCode statusCode = responseEntity1.getStatusCode();
        String uri2 = "http://localhost:" + port + "/ff-restaurants/api/v1/dishes/{id}";
        ResponseEntity<DishDto> responseEntity2 = restTemplate.exchange(
                uri2,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                },
                id
        );
        DishDto actualDishDto = responseEntity2.getBody();

        assertEquals(HttpStatus.OK, statusCode);
        assertNotNull("Response body is empty", actualDishDto);
        if (actualDishDto != null) {
            assertTrue("Missing field: id", Objects.equals(actualDishDto.getId(), id));
            assertTrue("Missing field: title", Objects.equals(actualDishDto.getTitle(), "Жаркое"));
            assertTrue("Missing field: restaurantTitle", Objects.equals(actualDishDto.getRestaurantTitle(), "Стейкхаус"));
            assertTrue("Missing field: calories", actualDishDto.getCalories() == 280);
            assertTrue("Missing field: proteins", actualDishDto.getProteins() == 6);
            assertTrue("Missing field: fats", actualDishDto.getFats() == 4);
            assertTrue("Missing field: carbohydrates", actualDishDto.getCarbohydrates() == 3);
            assertTrue("Missing field: groupDishTitle", Objects.equals(actualDishDto.getGroupDishTitle(), "Второе"));
            assertTrue("Missing field: categoryTitle", Objects.equals(actualDishDto.getCategoryTitle(), "Обед"));
            assertTrue("Missing field: createdAt", actualDishDto.getCreatedAt() != null);
            assertTrue("Missing field: updatedAt", actualDishDto.getUpdatedAt() != null);
        }

        dishesService.deleteDishById(id);
    }

    @Test
    public void deleteDishByIdEndpointTest() {
        DishDto dishDto2 = new DishDto("Название", "Диетолог", 72, 6, 4, 3, "Суп", "Ужин");
        HttpHeaders headers2 = new HttpHeaders();
        headers2.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<DishDto> requestEntity3 = new HttpEntity<>(dishDto2, headers2);
        URI uri3 = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port)
                .path("/ff-restaurants/api/v1/dishes")
                .build()
                .toUri();
        ResponseEntity<DishDto> responseEntity3 = restTemplate.exchange(
                uri3,
                HttpMethod.POST,
                requestEntity3,
                DishDto.class
        );
        Long id = Objects.requireNonNull(responseEntity3.getBody()).getId();
        URI uri4 = URI.create("http://localhost:" + port + "/ff-restaurants/api/v1/dishes/" + id);
        ResponseEntity<Void> responseEntity4 = restTemplate.exchange(
                uri4,
                HttpMethod.DELETE,
                null,
                Void.class
        );
        HttpStatusCode statusCode = responseEntity4.getStatusCode();
        assertEquals(HttpStatus.OK, statusCode);

        String uri5 = "http://localhost:" + port + "/ff-restaurants/api/v1/dishes/{id}";
        ResponseEntity<DishDto> deletedEntity = restTemplate.exchange(
                uri5,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                },
                id
        );
        assertNull(Objects.requireNonNull(deletedEntity.getBody()).getId());
    }
}
