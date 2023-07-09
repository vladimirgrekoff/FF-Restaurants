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
import ru.findFood.rest.services.CategoryService;
import ru.findFood.rest.validators.CategoryValidator;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
@Tag(name = "Категории", description = "Категории блюд")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryValidator categoryValidator;

    @Operation(
            summary = "Запрос на получение полного списка категорий блюд",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))}
                    )
            }
    )
    @GetMapping("/all")
    public List<CategoryDto> findAllCategories() {
        return categoryService.findAllCategories();
    }

    @Operation(
            summary = "Запрос на получение категории по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = CategoryDto.class))
                    ),
                    @ApiResponse(
                            description = "Категория не найдена", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = CategoryDto.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public CategoryDto findCategoryById(@PathVariable @Parameter(description = "Идентификатор категории", required = true) Long id){
        return categoryService.findById(id);
    }

    @Operation(
            summary = "Запрос на создание новой категории",
            responses = {
                    @ApiResponse(
                            description = "Категория успешно создана", responseCode = "201"
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewCategory(@RequestBody CategoryDto categoryDto) {
        categoryValidator.validate(categoryDto);
        categoryService.createNewCategory(categoryDto);
    }

    @Operation(
            summary = "Запрос на изменение категории",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateCategory(@RequestBody CategoryDto categoryDto) {
        categoryValidator.validate(categoryDto);
        categoryService.updateCategory(categoryDto);
    }

    @Operation(
            summary = "Запрос на удаление категории по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable @Parameter(description = "Идентификатор категории", required = true) Long id) {
        categoryService.deleteCategoryById(id);
    }
}
