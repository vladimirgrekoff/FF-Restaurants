package ru.findFood.rest.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.findFood.rest.converters.RestaurantRequestConverter;
import ru.findFood.rest.dtos.RestaurantRequestDto;
import ru.findFood.rest.dtos.UpdateRequestItemDto;
import ru.findFood.rest.services.RestaurantRequestsService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/requests")
@RequiredArgsConstructor
@Tag(name = "Запросы ресторана", description = "Методы работы с запросами  ресторанов")
public class RequestsController {

    private final RestaurantRequestsService restaurantRequestsService;
    private final RestaurantRequestConverter restaurantRequestConverter;

    // http://localhost:8189/ff-restaurants/api/v1/requests

    @Operation(
            summary = "Запрос на получение списка запросов ресторана диетологу по restaurant_title",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = RestaurantRequestDto.class)))}
                    )
            }
    )
    @GetMapping("/{restaurant_title}")
    public List<RestaurantRequestDto> getRestaurantRequests(@PathVariable @Parameter(description = "Название ресторана", required = true) String restaurant_title) {

        return restaurantRequestsService.findRestaurantRequests(restaurant_title).stream().map(restaurantRequestConverter::entityToDto).collect(Collectors.toList());
    }

    @Operation(
            summary = "Запрос на создание нового запроса ресторана диетологу по названию ресторана",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "201"
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createRequest(@RequestBody String restaurant_title) {
        restaurantRequestsService.createRequest(restaurant_title);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateRequestById(@RequestBody @Parameter(description = "Обработанное блюдо", required = true) UpdateRequestItemDto updateRequestItemDto) {
        restaurantRequestsService.updateRequest(updateRequestItemDto);
    }
}
