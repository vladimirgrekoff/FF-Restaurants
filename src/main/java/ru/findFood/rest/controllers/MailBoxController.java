package ru.findFood.rest.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.findFood.rest.converters.MailBoxConverter;
import ru.findFood.rest.dtos.MailBoxDto;
import ru.findFood.rest.dtos.ValueResponseDto;
import ru.findFood.rest.services.MailBoxService;

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
                            content = @Content(schema = @Schema(implementation = ValueResponseDto.class))
                    )
            }
    )
    @GetMapping("/generate_id")
    public ValueResponseDto generateRestMailBoxId() {
        return new ValueResponseDto(mailBoxService.generateMailBoxUuid());
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
    @GetMapping("/{uuid}")
    public MailBoxDto getCurrentMailBox(@RequestHeader(required = false) String username, @PathVariable String uuid) {

        return mailBoxConverter.entityToDto(mailBoxService.getCurrentMailBox(uuid));
    }


    @Operation(
            summary = "Запрос на добавление в запрос блюда по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    @GetMapping("/{uuid}/add/{dishId}")
    public void addDishToMailBox(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long dishId) {

        mailBoxService.addToMailBox(uuid, dishId);
    }

    @Operation(
            summary = "Запрос на удаление из запроса блюда по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    @DeleteMapping("/{uuid}/delete/{dishId}")
    public void deleteDishFromMailBox(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable(name = "dishId") Long dishId) {

        mailBoxService.deleteFromMailBox(uuid, dishId);
    }

    @Operation(
            summary = "Запрос на очистку запроса",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200"
                    )
            }
    )
    @DeleteMapping("/{uuid}/clear")
    public void clearCurrentMailBox(@RequestHeader(required = false) String username, @PathVariable String uuid) {

        mailBoxService.clearMailBox(uuid);
    }





}


