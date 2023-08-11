package ru.findFood.rest.dtos;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Schema(description = "Модель запроса диетологу")
public class MailBoxDto {
    @Schema(description = "Элементы запроса диетологу", requiredMode = Schema.RequiredMode.AUTO, example = "13")
    private List<MailBoxItemDto> items;

    public MailBoxDto(List<MailBoxItemDto> items) {
        this.items = items;
    }
}
