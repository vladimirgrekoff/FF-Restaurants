package ru.findFood.rest.validators;

import org.springframework.stereotype.Component;
import ru.findFood.rest.dtos.RestaurantDto;
import ru.findFood.rest.dtos.NewRestaurantDto;
import ru.findFood.rest.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantValidator {
    public void validate(RestaurantDto restaurantDto) {
        List<String> errors = new ArrayList<>();

        String restaurantTitle = restaurantDto.getTitle();
        if (restaurantTitle.isBlank() || restaurantTitle.isEmpty()) {
            errors.add("Название ресторана должно быть указано");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    public void validate(NewRestaurantDto newRestaurantDto) {
        List<String> errors = new ArrayList<>();

        String restaurantTitle = newRestaurantDto.getTitle();
        if (restaurantTitle.isBlank() || restaurantTitle.isEmpty()) {
            errors.add("Название ресторана должно быть указано");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
