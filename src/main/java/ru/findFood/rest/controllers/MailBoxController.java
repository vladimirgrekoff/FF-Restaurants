package ru.findFood.rest.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.findFood.rest.converters.MailBoxConverter;
import ru.findFood.rest.converters.MailBoxItemConverter;
import ru.findFood.rest.dtos.MailBoxDto;
import ru.findFood.rest.dtos.StringResponse;
import ru.findFood.rest.services.MailBoxService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/mail_box")
@RequiredArgsConstructor
@Tag(name = "Запросы диетологу", description = "Методы работы с запросами диетологу и выбранными блюдами")
public class MailBoxController {
    private final MailBoxService mailBoxService;
    private final MailBoxItemConverter mailBoxItemConverter;
    private final MailBoxConverter mailBoxConverter;

    @Operation(
            summary = "Запрос на получение идентификатора почтового ящика",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = StringResponse.class))
                    )
            }
    )
    @GetMapping("/generate_id")
    public StringResponse generateGuestMailBoxId() {
        return new StringResponse(UUID.randomUUID().toString());
    }

    @Operation(
            summary = "Запрос на получение текущего запроса со списком блюд",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = MailBoxDto.class))
                    )
            }
    )
    @GetMapping("/{guestMailBoxId}")
    public MailBoxDto getCurrentMailBox(@RequestHeader(required = false) String username, @PathVariable String guestMailBoxId) {
        String currentMailBoxId = selectMailBoxId(username, guestMailBoxId);

        return mailBoxConverter.entityToDto(mailBoxService.getCurrentMailBox(currentMailBoxId));
    }


    @Operation(
            summary = "Запрос на добавление в запрос блюда по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    @GetMapping("/{guestMailBoxId}/add/{dishId}")
    public void addDishToMailBox(@RequestHeader(required = false) String username, @PathVariable String guestMailBoxId, @PathVariable Long dishId) {
        String currentMailBoxId = selectMailBoxId(username, guestMailBoxId);
        mailBoxService.addToMailBox(currentMailBoxId, dishId);
    }

    @Operation(
            summary = "Запрос на удаление из запроса блюда по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    @DeleteMapping("/{guestMailBoxId}/delete/{dishId}")
    public void deleteDishFromMailBox(@RequestHeader(required = false) String username, @PathVariable String guestMailBoxId, @PathVariable(name = "dishId") Long dishId) {
        String currentMailBoxId = selectMailBoxId(username, guestMailBoxId);
        mailBoxService.deleteFromMailBox(currentMailBoxId, dishId);
    }

    @Operation(
            summary = "Запрос на очистку запроса",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    @DeleteMapping("/{guestMailBoxId}/clear")
    public void clearCurrentMailBox(@RequestHeader(required = false) String username, @PathVariable String guestMailBoxId) {
        String currentMailBoxId = selectMailBoxId(username, guestMailBoxId);
        mailBoxService.clearMailBox(currentMailBoxId);
    }

    private String selectMailBoxId(String username, String guestMailBoxId) {
        if (username != null) {
            return username;
        }
        return guestMailBoxId;
    }

}


