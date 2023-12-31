package ru.findFood.rest.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Модель группы блюд")
public class GroupDishDto {

    @Schema(description = "ID группы блюд",  requiredMode = Schema.RequiredMode.AUTO, example = "4")
    Long id;

    @Schema(description = "Имя группы блюд",  requiredMode = Schema.RequiredMode.AUTO, example = "Супы")
    private String title;

    @Schema(description = "Дата добавления",  requiredMode = Schema.RequiredMode.AUTO)
    private LocalDateTime createdAt;

    @Schema(description = "Дата последнего обновления",  requiredMode = Schema.RequiredMode.AUTO)
    private LocalDateTime updatedAt;

}
