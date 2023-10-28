package ru.findFood.rest;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import ru.findFood.rest.converters.RestaurantInfoConverter;
import ru.findFood.rest.dtos.RestaurantInfoDto;
import ru.findFood.rest.entities.Restaurant;
import ru.findFood.rest.services.RestaurantService;
import ru.findFood.rest.utils.Constants;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestaurantInfoControllerTests {


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantInfoConverter restaurantInfoConverter;


    @Test
    public void readAllRestaurantInfosEndpointTest() {
        String url = "http://localhost:" + port + "/ff-restaurants/api/v1/restaurants//info/all";
        ResponseEntity<List<RestaurantInfoDto>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        List<RestaurantInfoDto> restaurantInfos = responseEntity.getBody();
        HttpStatusCode statusCode = responseEntity.getStatusCode();

        assertEquals(200, statusCode.value());
        assertTrue("Response body is empty", restaurantInfos != null && !restaurantInfos.isEmpty());

        if (restaurantInfos != null) {
            for (RestaurantInfoDto restaurantInfoDto : restaurantInfos) {
                assertTrue("Missing field: id", restaurantInfoDto.getId() != null);
                assertTrue("Missing field: restaurantId", restaurantInfoDto.getRestaurantId() != null);
                assertTrue("Missing field: createdAt", restaurantInfoDto.getCreatedAt() != null);
            }
        }
        int actualCount = 0;
        if (restaurantInfos != null) {
            actualCount = restaurantInfos.size();
        }
        int expectedCount = Constants.INMEMORYDATABASERESTAURANTSSCOUNT;
        assertEquals(expectedCount, actualCount);
        assertEquals(expectedCount, actualCount);
    }

    @Test
    public void readRestaurantInfoByIdEndpointTest() {
        Long expectedRestaurantInfoId = 1L;
        String url = "http://localhost:" + port + "/ff-restaurants/api/v1/restaurants/info/{id}";
        ResponseEntity<RestaurantInfoDto> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                },
                expectedRestaurantInfoId
        );
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        RestaurantInfoDto restaurantInfoDto = responseEntity.getBody();

        assertEquals(200, statusCode.value());
        assertNotNull("Response body is empty", restaurantInfoDto);
        if (restaurantInfoDto != null) {
            assertTrue("Missing field: id", restaurantInfoDto.getId() != null);
            Long actualRestaurantInfoId = restaurantInfoDto.getId();
            assertEquals(expectedRestaurantInfoId, actualRestaurantInfoId);
            assertTrue("Missing field: restaurantId", restaurantInfoDto.getRestaurantId() != null);
            assertTrue("Missing field: createdAt", restaurantInfoDto.getCreatedAt() != null);
        }
    }

    @Test
    public void readRestaurantInfoByRestaurantIdEndpointTest() {
        Long restaurantId = 1L;
        String url = "http://localhost:" + port + "/ff-restaurants/api/v1/restaurants/info/restaurant/{id}";
        ResponseEntity<RestaurantInfoDto> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                },
                restaurantId
        );
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        RestaurantInfoDto restaurantInfoDto = responseEntity.getBody();

        assertEquals(200, statusCode.value());
        assertNotNull("Response body is empty", restaurantInfoDto);
        if (restaurantInfoDto != null) {
            assertTrue("Missing field: id", restaurantInfoDto.getId() != null);
            Long actualRestaurantId = restaurantInfoDto.getRestaurantId();
            assertTrue("Missing field: restaurantId", actualRestaurantId != null);
            assertEquals(restaurantId, actualRestaurantId);
            assertTrue("Missing field: createdAt", restaurantInfoDto.getCreatedAt() != null);
        }
    }

    @Test
    public void updateRestaurantInfoEndpointTest() {
        Faker faker = new Faker();
        Restaurant restaurant = new Restaurant(faker.company().name());
//        restaurantService.createNewRestaurant(restaurant);////////////////////////////////////////
        RestaurantInfoDto restaurantInfoDto = restaurantInfoConverter.entityToDto(restaurant.getRestaurantInfo());
        restaurantInfoDto.setDescription("Описание ресторана");
        restaurantInfoDto.setAddress("Адрес ресторана");
        restaurantInfoDto.setCuisines("Кухня ресторана");
        restaurantInfoDto.setEmail("mail@restaurant.com");
        restaurantInfoDto.setOpenHours("24/7");
        restaurantInfoDto.setPhoneNumber("89002002020");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RestaurantInfoDto> requestEntity = new HttpEntity<>(restaurantInfoDto, headers);
        URI uri = URI.create("http://localhost:" + port + "/ff-restaurants/api/v1/restaurants/info");
        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                uri,
                HttpMethod.PUT,
                requestEntity,
                Void.class
        );
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        String uri1 = "http://localhost:" + port + "/ff-restaurants/api/v1/restaurants/info/{id}";
        ResponseEntity<RestaurantInfoDto> responseEntity1 = restTemplate.exchange(
                uri1,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                },
                restaurantInfoDto.getId()
        );
        RestaurantInfoDto actualRestaurantInfoDto = responseEntity1.getBody();

        assertEquals(HttpStatus.OK, statusCode);
        assertNotNull("Response body is empty", actualRestaurantInfoDto);
        if (actualRestaurantInfoDto != null) {
            assertTrue("Missing field: id", Objects.equals(actualRestaurantInfoDto.getId(), restaurantInfoDto.getId()));
            assertTrue("Missing field: description", Objects.equals(actualRestaurantInfoDto.getDescription(), "Описание ресторана"));
            assertTrue("Missing field: address", Objects.equals(actualRestaurantInfoDto.getAddress(), "Адрес ресторана"));
            assertTrue("Missing field: cuisines", Objects.equals(actualRestaurantInfoDto.getCuisines(), "Кухня ресторана"));
            assertTrue("Missing field: e-mail", Objects.equals(actualRestaurantInfoDto.getEmail(), "mail@restaurant.com"));
            assertTrue("Missing field: open hours", Objects.equals(actualRestaurantInfoDto.getOpenHours(), "24/7"));
            assertTrue("Missing field: phone number", Objects.equals(actualRestaurantInfoDto.getPhoneNumber(), "89002002020"));
        }

        restaurantService.deleteRestaurantById(restaurant.getId());
    }
}
