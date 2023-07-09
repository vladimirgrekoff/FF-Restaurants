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
import ru.findFood.rest.dtos.RestaurantInfoDto;
import ru.findFood.rest.services.RestaurantInfoService;
import ru.findFood.rest.validators.RestaurantInfoValidator;

import java.util.List;

@RestController
@RequestMapping("api/v1/restaurants/info")
@RequiredArgsConstructor
@Tag(name = "Информация о ресторанах", description = "Информация о ресторанах")
public class RestaurantInfoController {
    private final RestaurantInfoValidator restaurantInfoValidator;
    private final RestaurantInfoService restaurantInfoService;


    @Operation(
            summary = "Запрос на получение полного списка информаций о ресторанах",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = RestaurantInfoDto.class)))}
                    )
            }
    )
    @GetMapping("/all")
    public List<RestaurantInfoDto> findAllRestaurantInfos() {
        return restaurantInfoService.findAll();
    }

    @Operation(
            summary = "Запрос на получение информации о ресторане по id информации",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = RestaurantInfoDto.class))
                    ),
                    @ApiResponse(
                            description = "Информация о ресторане не найдена", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = RestaurantInfoDto.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public RestaurantInfoDto findRestaurantInfoById(@PathVariable @Parameter(description = "Идентификатор информации о ресторане", required = true) Long id){
        return restaurantInfoService.findById(id);
    }

    @Operation(
            summary = "Запрос на получение информации о ресторане по id связанного ресторана",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = RestaurantInfoDto.class))
                    ),
                    @ApiResponse(
                            description = "Информация о ресторане с таким id не найдена", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = RestaurantInfoDto.class))
                    )
            }
    )
    @GetMapping("/{restaurantId}")
    public RestaurantInfoDto findRestaurantInfoByRestaurantId(@PathVariable @Parameter(description = "Идентификатор ресторана", required = true) Long restaurantId){
        return restaurantInfoService.findByRestaurantId(restaurantId);
    }

    @Operation(
            summary = "Запрос на создание новой информации о ресторане",
            responses = {
                    @ApiResponse(
                            description = "Информация о ресторане успешно создана", responseCode = "201"
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewRestaurantInfo(@RequestBody RestaurantInfoDto restaurantInfoDto) {
        restaurantInfoValidator.validate(restaurantInfoDto);
     //   restaurantInfoService.createNewRestaurantInfo(restaurantInfoDto);
    }

    @Operation(
            summary = "Запрос на изменение информации о ресторане",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateRestaurantInfo(@RequestBody RestaurantInfoDto restaurantInfoDto) {
        restaurantInfoValidator.validate(restaurantInfoDto);
     //   restaurantInfoService.update(restaurantInfoDto);
    }

    @Operation(
            summary = "Запрос на удаление информации о ресторане по ее id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )

    @DeleteMapping("/{id}")
    public void deleteRestaurantInfoById(@PathVariable @Parameter(description = "Идентификатор информации о ресторане", required = true) Long id) {
      //  restaurantInfoService.deleteById(id);
    }

}
