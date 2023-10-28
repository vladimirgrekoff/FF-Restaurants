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
import ru.findFood.rest.converters.RestaurantInfoConverter;
import ru.findFood.rest.dtos.EmailToNewEmail;
import ru.findFood.rest.dtos.RestaurantInfoDto;
import ru.findFood.rest.entities.RestaurantInfo;
import ru.findFood.rest.exceptions.AppError;
import ru.findFood.rest.services.RestaurantInfoService;
import ru.findFood.rest.services.RestaurantService;
import ru.findFood.rest.validators.RestaurantInfoValidator;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants/info")
@RequiredArgsConstructor
@Tag(name = "Информация о ресторанах", description = "Методы работы информацией о ресторанах")
public class RestaurantInfoController {
    private final RestaurantInfoService restaurantInfoService;
    private final RestaurantInfoValidator restaurantInfoValidator;
    private final RestaurantInfoConverter restaurantInfoConverter;


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
        List<RestaurantInfo> restaurantInfoList = restaurantInfoService.findAll();
        List<RestaurantInfoDto> restaurantInfoDtoList = new ArrayList<>();
        for(RestaurantInfo ri: restaurantInfoList){
            restaurantInfoDtoList.add(restaurantInfoConverter.entityToDto(ri));
        }
        return restaurantInfoDtoList;
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
        return restaurantInfoConverter.entityToDto(restaurantInfoService.findById(id));
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
    @GetMapping("/restaurant/{id}")
    public RestaurantInfoDto findRestaurantInfoByRestaurantId(@PathVariable @Parameter(description = "Идентификатор ресторана", required = true) Long id){
        return restaurantInfoConverter.entityToDto(restaurantInfoService.findByRestaurantId(id));
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
//        restaurantInfoValidator.validate(restaurantInfoDto);
        restaurantInfoService.updateRestaurantInfo(restaurantInfoConverter.dtoToEntity(restaurantInfoDto));
    }
    @Operation(
            summary = "Запрос на изменение email ресторана",
            responses = {
                    @ApiResponse(
                            description = "Email успешно обновлен", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = HttpStatus.class))
                    ),
                    @ApiResponse(
                            description = "Ошибка обновления email",responseCode = "400",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )
    @PutMapping("/change/email")
    @ResponseStatus(HttpStatus.OK)
    public void updateRestaurantEmail(@RequestBody @Parameter(description = "Новый email пользователя", required = true) EmailToNewEmail emailToNewEmail){
        restaurantInfoService.changeRestaurantEmail(emailToNewEmail);
    }
}
