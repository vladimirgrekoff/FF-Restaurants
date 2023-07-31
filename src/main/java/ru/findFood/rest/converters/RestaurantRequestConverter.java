package ru.findFood.rest.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.findFood.rest.dtos.RestaurantRequestDto;
import ru.findFood.rest.entities.RestaurantRequest;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RestaurantRequestConverter {
    private final RestaurantRequestItemConverter RestaurantRequestItemConverter;

    public RestaurantRequestDto entityToDto(RestaurantRequest rq) {
        return new RestaurantRequestDto(rq.getId(), rq.getRestaurantName(), rq.getRestaurantRequestItems().stream().map(RestaurantRequestItemConverter::entityToDto).collect(Collectors.toList()), rq.getCreatedAt());
    }
}
