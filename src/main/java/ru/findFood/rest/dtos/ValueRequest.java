package ru.findFood.rest.dtos;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Запрос по значению")
public class ValueRequest {
    @Schema(description = "Передаваемое значение", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private String value;
}
