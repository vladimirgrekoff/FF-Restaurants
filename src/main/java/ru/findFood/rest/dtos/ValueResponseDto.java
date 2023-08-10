package ru.findFood.rest.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Ответ на запрос")
public class ValueResponseDto {
    @Schema(description = "Запрашиваемое значение", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private String value;

}
