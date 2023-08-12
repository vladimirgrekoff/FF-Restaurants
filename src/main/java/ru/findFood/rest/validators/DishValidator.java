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



        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
