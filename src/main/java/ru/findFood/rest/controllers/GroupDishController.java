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
import ru.findFood.rest.dtos.CategoryDto;
import ru.findFood.rest.dtos.GroupDishDto;
import ru.findFood.rest.services.GroupDishService;
import ru.findFood.rest.validators.GroupDishValidator;

import java.util.List;

@RestController
@RequestMapping("api/v1/groups_of_dishes")
@RequiredArgsConstructor
@Tag(name = "Группы блюд", description = "Группы блюд")
public class GroupDishController {
    private final GroupDishService groupDishService;
    private final GroupDishValidator groupDishValidator;

    @Operation(
            summary = "Запрос на получение полного списка групп блюд",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = GroupDishDto.class)))}
                    )
            }
    )
    @GetMapping("/all")
    public List<GroupDishDto> findAllGroupsOfDishes() {
        return groupDishService.findAllGroupDishes();
    }

    @Operation(
            summary = "Запрос на получение группы блюд по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = CategoryDto.class))
                    ),
                    @ApiResponse(
                            description = "Группа блюд не найдена", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = CategoryDto.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public GroupDishDto findGroupDishById(@PathVariable @Parameter(description = "Идентификатор группы блюд", required = true) Long id){
        return groupDishService.findById(id);
    }

    @Operation(
            summary = "Запрос на создание новой группы блюд",
            responses = {
                    @ApiResponse(
                            description = "Группа блюд успешно создана", responseCode = "201"
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewGroupDish(@RequestBody GroupDishDto groupDishDto) {
        groupDishValidator.validate(groupDishDto);
        groupDishService.createNewGroupDish(groupDishDto);
    }

    @Operation(
            summary = "Запрос на изменение группы блюд",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateGroupDish(@RequestBody GroupDishDto groupDishDto) {
        groupDishValidator.validate(groupDishDto);
        groupDishService.updateGroupDish(groupDishDto);
    }

    @Operation(
            summary = "Запрос на удаление группы блюд по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    @DeleteMapping("/{id}")
    public void deleteGroupDishById(@PathVariable @Parameter(description = "Идентификатор группы блюд", required = true) Long id) {
        groupDishService.deleteGroupDishById(id);
    }
}
