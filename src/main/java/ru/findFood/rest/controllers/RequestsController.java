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
@Tag(name = "Заказы", description = "Методы работы с заказами продуктов")
public class RequestsController {

    private final RestaurantRequestsService restaurantRequestsService;
    private final RestaurantRequestConverter restaurantRequestConverter;

    // http://localhost:8189/ff-restaurants/api/v1/requests

    @Operation(
            summary = "Запрос на получение списка заказов ресторана по restaurant_name",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = RestaurantRequestDto.class)))}
                    )
            }
    )
    @GetMapping("/{restaurant_name}")
    public List<RestaurantRequestDto> getRestaurantRequests(@PathVariable @Parameter(description = "Название ресторана", required = true) String restaurant_name) {

        return restaurantRequestsService.findRestaurantRequests(restaurant_name).stream().map(restaurantRequestConverter::entityToDto).collect(Collectors.toList());
    }

    @Operation(
            summary = "Запрос на создание нового заказа по названию ресторана",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "201"
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createRequest(@RequestBody String restaurant_name) {
        restaurantRequestsService.createRequest(restaurant_name);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateRequestById(@RequestBody @Parameter(description = "Обработанное блюдо", required = true) UpdateRequestItemDto updateRequestItemDto) {
        restaurantRequestsService.updateRequest(updateRequestItemDto);
    }
}
