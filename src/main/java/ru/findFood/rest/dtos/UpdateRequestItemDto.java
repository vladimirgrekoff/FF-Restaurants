package ru.findFood.rest.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Обновление обработанных записей в запросе")
public class UpdateRequestItemDto {
    @Schema(description = "ID записи",  requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long Id;

    @Schema(description = "ID запроса",  requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private Long requestId;

    @Schema(description = "ID блюда",  requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
    private Long dishId;

    @Schema(description = "Здоровое питание",  requiredMode = Schema.RequiredMode.REQUIRED, example = "false")
    private Boolean dishHealthy;

    @Schema(description = "Одобрено диетологом",  requiredMode = Schema.RequiredMode.REQUIRED, example = "false")
    private Boolean dishApproved;

    @Schema(description = "Проверено",  requiredMode = Schema.RequiredMode.REQUIRED, example = "false")
    private Boolean verified;

}
