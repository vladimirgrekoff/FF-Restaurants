package ru.findFood.rest.dtos;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Модель запроса диетологу")
public class MailBoxDto {
    @Schema(description = "Элементы запроса диетологу", requiredMode = Schema.RequiredMode.AUTO, example = "13")
    private List<MailBoxItemDto> items;


}
