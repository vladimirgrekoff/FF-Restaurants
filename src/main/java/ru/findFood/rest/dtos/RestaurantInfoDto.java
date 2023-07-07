package ru.findFood.rest.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.findFood.rest.entities.Restaurant;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Информация о ресторане")
public class RestaurantInfoDto {

    @Schema(description = "ID информации о ресторане", requiredMode = Schema.RequiredMode.AUTO, example = "1")
    private Long id;

    @Schema(description = "ID и название ресторана",  requiredMode = Schema.RequiredMode.REQUIRED, example = "1, Стейкхаус")
    private Restaurant restaurant;

    @Schema(description = "Описание",  requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 1024, minLength = 0, example = "В нашем ресторане вы попробуете лучшие блюда европейской и американской кухонь, а также знаменитые стейки из отборной говядины!")
    private String description;

    @Schema(description = "Кухни",  requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 255, minLength = 0, example = "Европейская, Американская")
    private String cuisines;

    @Schema(description = "Адрес",  requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 1024, minLength = 10, example = "Москва, ул. Электрозаводская, дом 3 стр. 1")
    private String address;

    @Schema(description = "Номер телефона",  requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 255, minLength = 11, example = "+7(911)300-00-00")
    private String phoneNumber;

    @Schema(description = "E-mail",  requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 255, minLength = 6, example = "stake@house.ru")
    private String email;

    @Schema(description = "Часы работы",  requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 255, minLength = 1, example = "9:00 - 23:00")
    private String openHours;

    @Schema(description = "Дата добавления",  requiredMode = Schema.RequiredMode.AUTO)
    private LocalDateTime createdAt;

    @Schema(description = "Дата последнего обновления",  requiredMode = Schema.RequiredMode.AUTO)
    private LocalDateTime updatedAt;
}
