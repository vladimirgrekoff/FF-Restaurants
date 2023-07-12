package ru.findFood.rest.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.findFood.rest.entities.Restaurant;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Schema(description = "Модель блюда")
public class DishDto {

    @Schema(description = "ID блюда", requiredMode = Schema.RequiredMode.AUTO, example = "13")
    private Long id;

    @Schema(description = "Наименование блюда",  requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 255, minLength = 3, example = "Борщ")
    private String title;

    @Schema(description = "Ресторан",  requiredMode = Schema.RequiredMode.REQUIRED, example = "Диетолог")
    private String restaurant_title;

    @Schema(description = "Описание блюда",  requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 255, minLength = 3, example = "Борщ с говядиной, ломтиком сала на хлебном чипсе и сметаной")
    private String description;

    @Schema(description = "Цена блюда",  requiredMode = Schema.RequiredMode.REQUIRED, example = "360.00")
    private BigDecimal price;

//    @Schema(description = "Ссылка на фото",  requiredMode = Schema.RequiredMode.REQUIRED, example = "")
//    private byte[] image;

    @Schema(description = "Калории",  requiredMode = Schema.RequiredMode.REQUIRED, example = "58")
    private Integer calories;

    @Schema(description = "Белки",  requiredMode = Schema.RequiredMode.REQUIRED, example = "4")
    private Integer proteins;

    @Schema(description = "Жиры",  requiredMode = Schema.RequiredMode.REQUIRED, example = "3")
    private Integer fats;

    @Schema(description = "Углеводы",  requiredMode = Schema.RequiredMode.REQUIRED, example = "4")
    private Integer carbohydrates;

    @Schema(description = "Одобрено диетологом",  requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 255, minLength = 3, example = "true")
    private Boolean healthy;

    @Schema(description = "К/Б/Ж/У подтверждаю",  requiredMode = Schema.RequiredMode.REQUIRED, example = "true")
    private Boolean approved;

    @Schema(description = "Группа блюда",  requiredMode = Schema.RequiredMode.REQUIRED, example = "Суп")
    private String group_dish_title;

    @Schema(description = "Категория блюда",  requiredMode = Schema.RequiredMode.REQUIRED, example = "Обед")
    private String category_title;

    @Schema(description = "Дата добавления",  requiredMode = Schema.RequiredMode.AUTO)
    private LocalDateTime createdAt;

    @Schema(description = "Дата последнего обновления",  requiredMode = Schema.RequiredMode.AUTO)
    private LocalDateTime updatedAt;


    public DishDto(Long id, String title, String restaurant_title, String description, BigDecimal price, /*byte[] image,*/ Integer calories, Integer proteins, Integer fats, Integer carbohydrates, Boolean healthy, Boolean approved, String group_dish_title, String category_title) {


        this.id = id;
        this.title = title;
        this.restaurant_title = restaurant_title;
        this.description = description;
        this.price = price;
//        this.image = image;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.healthy = healthy;
        this.approved = approved;
        this.group_dish_title = group_dish_title;
        this.category_title = category_title;
    }
}
