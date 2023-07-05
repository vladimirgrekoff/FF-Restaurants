package ru.findFood.rest.converters;

import org.springframework.stereotype.Component;
import ru.findFood.rest.dtos.GroupDishDto;
import ru.findFood.rest.entities.GroupDish;

@Component
public class GroupDishDtoConverter {

    public GroupDish dtoToEntity(GroupDishDto groupDishDto) {
        GroupDish groupDish = new GroupDish();
        groupDish.setId(groupDishDto.getId());
        groupDish.setTitle(groupDishDto.getTitle());
        return groupDish;
    }

    public GroupDishDto entityToDto(GroupDish groupDish) {
        GroupDishDto groupDishDto = new GroupDishDto();
        groupDishDto.setId(groupDish.getId());
        groupDishDto.setId(groupDish.getId());
        return groupDishDto;
    }
}
