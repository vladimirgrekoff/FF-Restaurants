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
import ru.findFood.rest.dtos.NewRestaurantDto;
import ru.findFood.rest.dtos.RestaurantDto;
import ru.findFood.rest.services.RestaurantService;
import ru.findFood.rest.utils.Constants;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.util.AssertionErrors.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestaurantControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void readAllRestaurantsEndpointTest() {
        String url = "http://localhost:" + port + "/ff-restaurants/api/v1/restaurants/all";
        ResponseEntity<List<RestaurantDto>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        List<RestaurantDto> restaurants = responseEntity.getBody();
        HttpStatusCode statusCode = responseEntity.getStatusCode();

        assertEquals(200, statusCode.value());
        assertTrue("Response body is empty", restaurants != null && !restaurants.isEmpty());

        if (restaurants != null) {
            for (RestaurantDto restaurantDto : restaurants) {
                assertTrue("Missing field: id", restaurantDto.getId() != null);
                assertTrue("Missing field: title", restaurantDto.getTitle() != null);
                assertTrue("Missing field: restaurantInfoId", restaurantDto.getRestaurantInfoId() != null);
                assertTrue("Missing field: dishesList", restaurantDto.getDishesList() != null);
                assertTrue("Missing field: createdAt", restaurantDto.getCreatedAt() != null);
            }
        }
        int actualCount = 0;
        if (restaurants != null) {
            actualCount = restaurants.size();
        }
        int expectedCount = Constants.INMEMORYDATABASERESTAURANTSSCOUNT;
        assertEquals(expectedCount, actualCount);
        assertEquals(expectedCount, actualCount);
    }

    @Test
    public void readRestaurantByIdEndpointTest() {
        Long expectedRestaurantId = 1L;
        String url = "http://localhost:" + port + "/ff-restaurants/api/v1/restaurants/{id}";
        ResponseEntity<RestaurantDto> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                },
                expectedRestaurantId
        );
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        RestaurantDto restaurantDto = responseEntity.getBody();

        assertEquals(200, statusCode.value());
        assertNotNull("Response body is empty", restaurantDto);
        if (restaurantDto != null) {
            Long actualRestaurantId = restaurantDto.getId();
            assertTrue("Missing field: id", actualRestaurantId != null);
            assertEquals(expectedRestaurantId, actualRestaurantId);
            assertTrue("Missing field: title", restaurantDto.getTitle() != null);
            assertTrue("Missing field: restaurantInfoId", restaurantDto.getRestaurantInfoId() != null);
            assertTrue("Missing field: createdAt", restaurantDto.getCreatedAt() != null);
        }
    }

    @Test
    public void createNewRestaurantEndpointTest() throws IOException {
        Faker faker = new Faker();
        NewRestaurantDto newRestaurantDto = new NewRestaurantDto(faker.company().name());
        String title = newRestaurantDto.getTitle();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<NewRestaurantDto> requestEntity = new HttpEntity<>(newRestaurantDto, headers);
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port)
                .path("/ff-restaurants/api/v1/restaurants")
                .build()
                .toUri();
        ResponseEntity<RestaurantDto> responseEntity = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                requestEntity,
                RestaurantDto.class
        );
        HttpStatusCode statusCode = responseEntity.getStatusCode();
        RestaurantDto actualRestaurantDto = responseEntity.getBody();

        assertEquals(HttpStatus.CREATED, statusCode);
        assertNotNull("Response body is empty", actualRestaurantDto);
        if (actualRestaurantDto != null) {
            assertTrue("Missing field: id", actualRestaurantDto.getId() != null);
            assertTrue("Missing field: title", Objects.equals(actualRestaurantDto.getTitle(), title));
            assertTrue("Missing field: restaurantInfoId", actualRestaurantDto.getRestaurantInfoId() != null);
            assertTrue("Missing field: createdAt", actualRestaurantDto.getCreatedAt() != null);
        }

        restaurantService.deleteRestaurantById(actualRestaurantDto.getId());
    }

    @Test
    public void updateRestaurantEndpointTest() {
        Faker faker = new Faker();
        RestaurantDto restaurantDto = new RestaurantDto (faker.company().name());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RestaurantDto> requestEntity = new HttpEntity<>(restaurantDto, headers);
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port)
                .path("/ff-restaurants/api/v1/restaurants")
                .build()
                .toUri();
        ResponseEntity<RestaurantDto> responseEntity = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                requestEntity,
                RestaurantDto.class
        );
        Long restaurantId = Objects.requireNonNull(responseEntity.getBody()).getId();
        restaurantDto.setId(restaurantId);
        restaurantDto.setCreatedAt(responseEntity.getBody().getCreatedAt());
        restaurantDto.setTitle("Новое название");
        restaurantDto.setRestaurantInfoId(responseEntity.getBody().getRestaurantInfoId());
        HttpHeaders headers1 = new HttpHeaders();
        headers1.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RestaurantDto> requestEntity1 = new HttpEntity<>(restaurantDto, headers1);
        URI uri1 = URI.create("http://localhost:" + port + "/ff-restaurants/api/v1/restaurants");
        ResponseEntity<Void> responseEntity1 = restTemplate.exchange(
                uri1,
                HttpMethod.PUT,
                requestEntity1,
                Void.class
        );
        HttpStatusCode statusCode = responseEntity1.getStatusCode();
        String uri2 = "http://localhost:" + port + "/ff-restaurants/api/v1/restaurants/{id}";
        ResponseEntity<RestaurantDto> responseEntity2 = restTemplate.exchange(
                uri2,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                },
                restaurantId
        );
        RestaurantDto actualRestaurantDto = responseEntity2.getBody();

        assertEquals(HttpStatus.OK, statusCode);
        assertNotNull("Response body is empty", actualRestaurantDto);
        if (actualRestaurantDto != null) {
            assertTrue("Missing field: id", Objects.equals(actualRestaurantDto.getId(), restaurantId));
            assertTrue("Missing field: title", Objects.equals(actualRestaurantDto.getTitle(), "Новое название"));
        }

        restaurantService.deleteRestaurantById(restaurantId);

    }

    @Test
    public void deleteRestaurantByIdEndpointTest() {
        Faker faker = new Faker();
        RestaurantDto restaurantDto = new RestaurantDto (faker.company().name());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RestaurantDto> requestEntity = new HttpEntity<>(restaurantDto, headers);
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port)
                .path("/ff-restaurants/api/v1/restaurants")
                .build()
                .toUri();
        ResponseEntity<RestaurantDto> responseEntity = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                requestEntity,
                RestaurantDto.class
        );
        Long restaurantId = Objects.requireNonNull(responseEntity.getBody()).getId();
        URI uri1 = URI.create("http://localhost:" + port + "/ff-restaurants/api/v1/restaurants/" + restaurantId);
        ResponseEntity<Void> responseEntity1 = restTemplate.exchange(
                uri1,
                HttpMethod.DELETE,
                null,
                Void.class
        );
        HttpStatusCode statusCode = responseEntity1.getStatusCode();
        assertEquals(HttpStatus.OK, statusCode);

        String uri2 = "http://localhost:" + port + "/ff-restaurants/api/v1/restaurants/{id}";
        ResponseEntity<RestaurantDto> deletedEntity = restTemplate.exchange(
                uri2,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                },
                restaurantId
        );
        assertNull(Objects.requireNonNull(deletedEntity.getBody()).getId());
    }
}
