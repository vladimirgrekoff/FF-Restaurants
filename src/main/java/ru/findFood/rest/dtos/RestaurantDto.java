package ru.findFood.rest.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.findFood.rest.entities.RestaurantInfo;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Ресторан")
public class RestaurantDto {

    @Schema(description = "ID ресторана", requiredMode = Schema.RequiredMode.AUTO, example = "1")
    private Long id;

    @Schema(description = "Название",  requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 255, minLength = 1, example = "Стейкхаус")
    private String title;

    @Schema(description = "Информация о ресторане",  requiredMode = Schema.RequiredMode.REQUIRED, example = "Информация о ресторане Стейкхаус: описание, контакты, часы работы")
    private RestaurantInfo restaurantInfo;

    @Schema(description = "Дата добавления",  requiredMode = Schema.RequiredMode.AUTO)
    private LocalDateTime createdAt;

    @Schema(description = "Дата последнего обновления",  requiredMode = Schema.RequiredMode.AUTO)
    private LocalDateTime updatedAt;
}
