package ru.findFood.rest.validators;

import org.springframework.stereotype.Component;
import ru.findFood.rest.dtos.DishDto;
import ru.findFood.rest.exceptions.ValidationException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class DishValidator {
    public void validate(DishDto dishDto) {
        List<String> errors = new ArrayList<>();

//        if (dishDto.getPrice().compareTo(BigDecimal.ONE) < 0) {
//            errors.add("Цена блюда не может быть меньше 1");
//        }

//        if (dishDto.getTitle().isBlank()) {
//            errors.add("Блюдо не может иметь пустое название");
//        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
