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
    public StringResponse generateRestMailBoxId() {
        return new StringResponse(mailBoxService.generateMailBoxUuid());
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
    @GetMapping("/{restMailBoxId}")
    public MailBoxDto getCurrentMailBox(@RequestHeader(required = false) String username, @PathVariable String restMailBoxId) {

        return mailBoxConverter.entityToDto(mailBoxService.getCurrentMailBox(restMailBoxId));
    }


    @Operation(
            summary = "Запрос на добавление в запрос блюда по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    @GetMapping("/{restMailBoxId}/add/{dishId}")
    public void addDishToMailBox(@RequestHeader(required = false) String username, @PathVariable String restMailBoxId, @PathVariable Long dishId) {

        mailBoxService.addToMailBox(restMailBoxId, dishId);
    }

    @Operation(
            summary = "Запрос на удаление из запроса блюда по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    @DeleteMapping("/{restMailBoxId}/delete/{dishId}")
    public void deleteDishFromMailBox(@RequestHeader(required = false) String username, @PathVariable String restMailBoxId, @PathVariable(name = "dishId") Long dishId) {

        mailBoxService.deleteFromMailBox(restMailBoxId, dishId);
    }

    @Operation(
            summary = "Запрос на очистку запроса",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    @DeleteMapping("/{restMailBoxId}/clear")
    public void clearCurrentMailBox(@RequestHeader(required = false) String username, @PathVariable String restMailBoxId) {

        mailBoxService.clearMailBox(restMailBoxId);
    }





}


