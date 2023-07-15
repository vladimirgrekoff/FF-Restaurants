package ru.findFood.rest.validators;

import org.springframework.stereotype.Component;
import ru.findFood.rest.dtos.CategoryDto;
import ru.findFood.rest.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryValidator {
    public void validate(CategoryDto categoryDto){
        List<String> errors = new ArrayList<>();

        String categoryTitle = categoryDto.getTitle();
        if (categoryTitle.isBlank() || categoryTitle.isEmpty()) {
            errors.add("Название категории должно быть указано");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
