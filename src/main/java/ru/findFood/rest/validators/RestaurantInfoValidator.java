package ru.findFood.rest.validators;

import org.springframework.stereotype.Component;
import ru.findFood.rest.dtos.RestaurantInfoDto;
import ru.findFood.rest.exceptions.ResourceNotFoundException;
import ru.findFood.rest.exceptions.ValidationException;
import ru.findFood.rest.services.RestaurantInfoService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class RestaurantInfoValidator {
    private RestaurantInfoService restaurantInfoService;

    public void validate(RestaurantInfoDto restaurantInfoDto) {
        List<String> errors = new ArrayList<>();

        //проверка ID связанного ресторана
        Long restaurantId = restaurantInfoDto.getRestaurantId();
        if (restaurantId != null) {
            try {
                restaurantInfoService.findByRestaurantId(restaurantId);
            } catch (ResourceNotFoundException re) {
                errors.add(re.getMessage());
            }
        } else {
            errors.add("ID ресторана не может иметь нулевое значение");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }

        //проверка описания
        String restaurantDescription = restaurantInfoDto.getDescription();
        if (restaurantDescription.isBlank() || restaurantDescription.isEmpty()) {
            errors.add("Описание ресторана не может быть пустым");
        }
        if (restaurantDescription.length() > 1024) {
            errors.add("Сократите описание ресторана с " + restaurantDescription.length() + " до 1024 знаков");
        }

        //проверка кухни
        String restaurantCuisines = restaurantInfoDto.getCuisines();
        if (restaurantCuisines.isBlank() || restaurantCuisines.isEmpty()) {
            errors.add("Кухня ресторана должна быть указана");
        }
        if (restaurantCuisines.length() > 255) {
            errors.add("Сократите перечисление кухонь ресторана с " + restaurantCuisines.length() + " до 255 знаков");
        }

        //проверка адреса
        String restaurantAddress = restaurantInfoDto.getAddress();
        if (restaurantAddress.isBlank() || restaurantAddress.isEmpty()) {
            errors.add("Адрес ресторана не может быть пустым");
        }
        if (restaurantAddress.length() > 1024) {
            errors.add("Сократите адрес ресторана с " + restaurantAddress.length() + " до 1024 знаков");
        }

        //проверка номера телефона
        String restaurantPhoneNumber = restaurantInfoDto.getPhoneNumber();
        if (restaurantPhoneNumber.isBlank() || restaurantPhoneNumber.isEmpty()) {
            errors.add("Номер телефона ресторана должен быть указан");
        }
        if (restaurantPhoneNumber.length() > 255) {
            errors.add("Сократите номер телефона ресторана с " + restaurantPhoneNumber.length() + " до 255 знаков");
        }

        //проверка e-mail
        String restaurantEmail = restaurantInfoDto.getEmail();
        if (restaurantEmail.isBlank() || restaurantEmail.isEmpty()) {
            errors.add("E-mail адрес ресторана не может быть пустым");
        }
        if (!Pattern.matches("^[a-zA-Z\\d._%+-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$", restaurantEmail)) {
            errors.add("E-mail адрес ресторана введен некорректно");
        }

        //проверка часов работы
        String restaurantOpenHours = restaurantInfoDto.getOpenHours();
        if (restaurantOpenHours.isBlank() || restaurantOpenHours.isEmpty()) {
            errors.add("Часы работы ресторана должны быть указаны");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
