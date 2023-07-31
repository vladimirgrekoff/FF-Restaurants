package ru.findFood.rest.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Модель запроса")
public class RestaurantRequestDto {

    @Schema(description = "ID запроса",  requiredMode = Schema.RequiredMode.AUTO, example = "1")
    private Long id;

    @Schema(description = "Название ресторана",  requiredMode = Schema.RequiredMode.AUTO, example = "1")
    private String restaurantName;

    @Schema(description = "Список блюд в запросе")
    private List<RestaurantRequestItemDto> items;

    @Schema(description = "Дата запроса")
    private LocalDateTime createdAt;




}
