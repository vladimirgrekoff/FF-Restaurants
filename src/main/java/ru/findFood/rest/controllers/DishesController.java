package ru.findFood.rest.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.findFood.rest.converters.DishConverter;
import ru.findFood.rest.dtos.DishDto;
import ru.findFood.rest.dtos.MenuDishDTO;
import ru.findFood.rest.dtos.UpdateDishTimeRequest;
import ru.findFood.rest.entities.Dish;
import ru.findFood.rest.services.DishesService;
import ru.findFood.rest.validators.DishValidator;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/dishes")
@RequiredArgsConstructor
@Tag(name = "Блюда", description = "Методы работы с блюдами")
@Slf4j
public class DishesController {
    private final DishConverter dishConverter;
    private final DishValidator dishValidator;
    private final DishesService dishesService;

    // http://localhost:8189/ff-restaurants/api/v1/dishes

    //swagger:
    //http://localhost:8189/ff-restaurants/swagger-ui/index.html#

    //переписать на поиск по id ресторана
    @Operation(
            summary = "Запрос на получение полного списка блюд",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = DishDto.class)))}
                    )
            }
    )
    @GetMapping("/all")
    public List<DishDto> readAllDishes() {
        List<Dish> dishList = dishesService.findAll();
        List<DishDto> dishDtoList = new ArrayList<>();
        for (Dish d : dishList) {
            DishDto dishDto = dishConverter.entityToDto(d);
            dishDtoList.add(dishDto);
        }
        return dishDtoList;
    }

    @GetMapping("/limitedAndByCategory")
    public List<MenuDishDTO> getByCategory(@RequestParam String category, @RequestParam String querySize) {
        return dishesService.findByCategory(category, querySize)
                .stream()
                .map(dishConverter::entityToMenuDishDto)
                .toList();
    }

    @Operation(
            summary = "Запрос на получение списка блюд по Id ресторана",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = DishDto.class)))}
                    )
            }
    )
    @GetMapping("/restaurant/{id}")
    public List<DishDto> readAllDishesByRestaurantId(@PathVariable @Parameter(description = "Идентификатор ресторана", required = true) Long id) {
        List<Dish> dishList = dishesService.findAllByRestaurantId(id);
        List<DishDto> dishDtoList = new ArrayList<>();
        for (Dish d : dishList) {
            DishDto dishDto = dishConverter.entityToDto(d);
            dishDtoList.add(dishDto);
        }
        return dishDtoList;
    }

    @Operation(
            summary = "Запрос на получение блюда по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = DishDto.class))
                    ),
                    @ApiResponse(
                            description = "Блюдо не найдено", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = DishDto.class))
                    )
            }
    )

    @GetMapping("/{id}")
    public DishDto readDishById(@PathVariable @Parameter(description = "Идентификатор блюда", required = true) Long id) {
        return dishConverter.entityToDto(dishesService.findById(id));
    }

    @Operation(
            summary = "Запрос на создание нового блюда",
            responses = {
                    @ApiResponse(
                            description = "блюда успешно создано", responseCode = "201"
                    )
            }
    )

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DishDto createNewDish(@RequestBody DishDto dishDto) {
        dishValidator.validate(dishDto);
        return dishConverter.entityToDto(dishesService.createNewDish(dishConverter.dtoToEntity(dishDto)));
    }

    @Operation(
            summary = "Запрос на изменение параметров блюда",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateDish(@RequestBody DishDto dishDto) {
        dishValidator.validate(dishDto);
        dishesService.updateDish(dishConverter.dtoToEntity(dishDto));
    }

    @PostMapping("/updateUsedLastTime")
    @ResponseStatus(HttpStatus.OK)
    public void updateDishLastUsedTime(@RequestBody UpdateDishTimeRequest request) {
        dishesService.updateLastUsedTime(request);
    }


    @Operation(
            summary = "Запрос на удаление блюда по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )

    @DeleteMapping("/{id}")
    public void deleteDishById(@PathVariable @Parameter(description = "Идентификатор блюда", required = true) Long id) {
        dishesService.deleteDishById(id);
    }
}
