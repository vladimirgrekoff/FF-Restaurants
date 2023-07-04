package ru.findFood.rest.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import ru.findFood.rest.entities.GroupDish;

import java.math.BigDecimal;

@Schema(description = "Модель блюда")
public class DishDto {

    @Schema(description = "ID блюда", requiredMode = Schema.RequiredMode.AUTO, example = "13")
    private Long id;

    @Schema(description = "Наименование блюда",  requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 255, minLength = 3, example = "Суп")
    private String title;

    @Schema(description = "ID ресторана",  requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long restaurant_id;

    @Schema(description = "Описание блюда",  requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 255, minLength = 3, example = "Суп")
    private String description;

    @Schema(description = "Цена блюда",  requiredMode = Schema.RequiredMode.REQUIRED, example = "100.00")
    private BigDecimal price;

    @Schema(description = "Ссылка на фото",  requiredMode = Schema.RequiredMode.REQUIRED, example = "")
    private byte[] image;

    @Schema(description = "Калории",  requiredMode = Schema.RequiredMode.REQUIRED, example = "")
    private Integer calories;

    @Schema(description = "Белки",  requiredMode = Schema.RequiredMode.REQUIRED, example = "")
    private Integer proteins;

    @Schema(description = "Жиры",  requiredMode = Schema.RequiredMode.REQUIRED, example = "")
    private Integer fats;

    @Schema(description = "Углеводы",  requiredMode = Schema.RequiredMode.REQUIRED, example = "")
    private Integer carbohydrates;

    @Schema(description = "Группа блюда",  requiredMode = Schema.RequiredMode.REQUIRED, example = "Супы")
    private GroupDish group_dish_id;


}
