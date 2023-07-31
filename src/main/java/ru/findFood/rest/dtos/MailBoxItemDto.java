package ru.findFood.rest.dtos;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Модель элемента запроса диетологу")
public class MailBoxItemDto {
    @Schema(description = "ID блюда", requiredMode = Schema.RequiredMode.AUTO, example = "13")
    private Long dishId;
    @Schema(description = "Наименование блюда",  requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 255, minLength = 3, example = "Борщ")
    private String dishTitle;
    @Schema(description = "Описание блюда",  requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 255, minLength = 3, example = "Борщ с говядиной, ломтиком сала на хлебном чипсе и сметаной")
    private String dishDescription;
    @Schema(description = "Группа блюда",  requiredMode = Schema.RequiredMode.REQUIRED, example = "Суп")
    private String dishGroupDishTitle;
    @Schema(description = "Калории",  requiredMode = Schema.RequiredMode.REQUIRED, example = "58")
    private Integer dishCalories;
    @Schema(description = "Белки",  requiredMode = Schema.RequiredMode.REQUIRED, example = "4")
    private Integer dishProteins;
    @Schema(description = "Жиры",  requiredMode = Schema.RequiredMode.REQUIRED, example = "3")
    private Integer dishFats;
    @Schema(description = "Углеводы",  requiredMode = Schema.RequiredMode.REQUIRED, example = "4")
    private Integer dishCarbohydrates;
    @Schema(description = "Здоровое питание",  requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 255, minLength = 3, example = "false")
    private Boolean dishHealthy;
    @Schema(description = "Одобрено диетологом",  requiredMode = Schema.RequiredMode.REQUIRED, example = "false")
    private Boolean dishApproved;
}
