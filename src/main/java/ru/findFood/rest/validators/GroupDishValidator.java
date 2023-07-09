package ru.findFood.rest.validators;

import org.springframework.stereotype.Component;
import ru.findFood.rest.dtos.GroupDishDto;
import ru.findFood.rest.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupDishValidator {
    public void validate(GroupDishDto groupDishDto){
        List<String> errors = new ArrayList<>();

        String groupDishTitle = groupDishDto.getTitle();
        if (groupDishTitle.isBlank() || groupDishTitle.isEmpty()) {
            errors.add("Название группы блюд должно быть указано");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
