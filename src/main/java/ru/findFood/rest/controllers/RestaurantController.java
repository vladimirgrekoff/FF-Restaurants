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
import ru.findFood.rest.converters.RestaurantConverter;
import ru.findFood.rest.dtos.RestaurantDto;
import ru.findFood.rest.dtos.NewRestaurantDto;
import ru.findFood.rest.entities.Restaurant;
import ru.findFood.rest.services.RestaurantService;
import ru.findFood.rest.validators.RestaurantValidator;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
@Tag(name = "Рестораны", description = "Методы работы с ресторанами")
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final RestaurantValidator restaurantValidator;
    private final RestaurantConverter restaurantConverter;

    @Operation(
            summary = "Запрос на получение полного списка ресторанов (id и название)",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = RestaurantDto.class)))}
                    )
            }
    )
    @GetMapping("/all")
    public List<RestaurantDto> readAllRestaurants(
            @Parameter(description = "Название ресторана (полное или часть)")
            @RequestParam(name = "part_title", required = false) String partTitle
            ) {
        List<Restaurant> restaurantList = restaurantService.findAll(partTitle);
        List<RestaurantDto> restaurantDtoList = new ArrayList<>();
        for (Restaurant r: restaurantList) {
            RestaurantDto restaurantDto = restaurantConverter.entityToDto(r);
            restaurantDtoList.add(restaurantDto);
        }
        return restaurantDtoList;
    }

    @Operation(
            summary = "Запрос на получение ресторана по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = RestaurantDto.class))
                    ),
                    @ApiResponse(
                            description = "Ресторан не найден", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = RestaurantDto.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public RestaurantDto readRestaurantById(@PathVariable @Parameter(description = "Идентификатор ресторана", required = true) Long id){
        return restaurantConverter.entityToDto(restaurantService.findById(id));
    }

    @Operation(
            summary = "Запрос на получение ресторана по email",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = RestaurantDto.class))
                    ),
                    @ApiResponse(
                            description = "Ресторан не найден", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = RestaurantDto.class))
                    )
            }
    )
    @GetMapping("/email/{email}")
    public RestaurantDto readRestaurantByEmail(@PathVariable @Parameter(description = "адрес электронной почты ресторана", required = true) String email){

        return restaurantConverter.entityToDto(restaurantService.findByEmail(email));
    }

    @Operation(
            summary = "Запрос на получение ресторана по назвванию",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = RestaurantDto.class))
                    ),
                    @ApiResponse(
                            description = "Ресторан не найден", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = RestaurantDto.class))
                    )
            }
    )
    @GetMapping("/title/{title}")
    public RestaurantDto readRestaurantByTitle(@PathVariable @Parameter(description = "название ресторана", required = true) String title){

        return restaurantConverter.entityToDto(restaurantService.findByTitle(title));
    }

    @Operation(
            summary = "Запрос на проверку использования названия ресторана",
            responses = {
                    @ApiResponse(
                            description = "Название свободно", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Boolean.class))
                    ),
                    @ApiResponse(
                            description = "Название занято", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = Boolean.class))
                    )
            }
    )
    @GetMapping("/check/{title}")
    public Boolean isRestaurantTitleFree(@PathVariable @Parameter(description = "названия ресторана", required = true) String title){

        return restaurantService.isTitleFree(title);
    }

    @Operation(
            summary = "Запрос на создание нового ресторана",
            responses = {
                    @ApiResponse(
                            description = "Ресторан успешно создан", responseCode = "201"
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewRestaurant(@RequestBody NewRestaurantDto newRestaurantDto) {
        restaurantService.createNewRestaurant(new Restaurant(newRestaurantDto.getTitle()), newRestaurantDto.getEmail());
    }

    @Operation(
            summary = "Запрос на изменение ресторана",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateRestaurant(@RequestBody RestaurantDto restaurantDto) {
        restaurantValidator.validate(restaurantDto);
        restaurantService.updateRestaurant(restaurantConverter.dtoToEntity(restaurantDto));
    }

    @Operation(
            summary = "Запрос на удаление ресторана по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Ошибка удаления",responseCode = "400"
                    )
            }
    )

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRestaurantById(@PathVariable @Parameter(description = "Идентификатор ресторана", required = true) Long id) {

        restaurantService.deleteRestaurantById(id);
    }
}
