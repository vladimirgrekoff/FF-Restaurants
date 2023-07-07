package ru.findFood.rest.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.NoArgsConstructor;
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

    @Schema(description = "Одобрено диетологом",  requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 255, minLength = 3, example = "true")
    private Boolean healthy;

    @Schema(description = "Ресторан",  requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private RestaurantDto restaurantDto;

    @Schema(description = "Описание блюда",  requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 255, minLength = 3, example = "Борщ с говядиной, ломтиком сала на хлебном чипсе и сметаной")
    private String description;

    @Schema(description = "Цена блюда",  requiredMode = Schema.RequiredMode.REQUIRED, example = "360.00")
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

    @Schema(description = "К/Б/Ж/У подтверждаю",  requiredMode = Schema.RequiredMode.REQUIRED, example = "true")
    private Boolean approved;

    @Schema(description = "Группа блюда",  requiredMode = Schema.RequiredMode.REQUIRED, example = "Супы")
//    private String groupDishDto.getTitle;
    private GroupDishDto groupDishDto;

    @Schema(description = "Категория блюда",  requiredMode = Schema.RequiredMode.REQUIRED, example = "Завтрак")
    private CategoryDto categoryDto;

    @Schema(description = "Дата добавления",  requiredMode = Schema.RequiredMode.AUTO)
    private LocalDateTime createdAt;

    @Schema(description = "Дата последнего обновления",  requiredMode = Schema.RequiredMode.AUTO)
    private LocalDateTime updatedAt;


    public DishDto(Long id, String title, Boolean healthy, RestaurantDto restaurantDto, String description, BigDecimal price, byte[] image, Integer calories, Integer proteins, Integer fats, Integer carbohydrates, Boolean approved, CategoryDto categoryDto, GroupDishDto groupDishDto/*GroupDishDto*//* groupDishTitle*//*, *//**//*Long*//**//* GroupDishDto group_dish_id*/) {


        this.id = id;
        this.title = title;
        this.healthy = healthy;
        this.restaurantDto = restaurantDto;
        this.description = description;
        this.price = price;
        this.image = image;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.approved = approved;
        this.groupDishDto = groupDishDto;
        this.categoryDto = categoryDto;
    }
}
