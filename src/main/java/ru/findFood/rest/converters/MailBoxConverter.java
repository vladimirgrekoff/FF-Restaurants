package ru.findFood.rest.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.findFood.rest.dtos.MailBoxDto;
import ru.findFood.rest.models.MailBox;

import java.util.stream.Collectors;
@Component
@RequiredArgsConstructor
public class MailBoxConverter {
    private final MailBoxItemConverter mailBoxItemConverter;

    public MailBoxDto entityToDto(MailBox mailBox) {
        return new MailBoxDto(mailBox.getItems().stream().map(mailBoxItemConverter::entityToDto).collect(Collectors.toList()));
    }
}
