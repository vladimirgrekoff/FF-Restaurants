package ru.findFood.rest.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.findFood.rest.dtos.GroupDishDto;
import ru.findFood.rest.entities.GroupDish;
import ru.findFood.rest.services.GroupDishService;

@Component
@RequiredArgsConstructor
public class GroupDishConverter {
    private final GroupDishService groupDishService;

    public GroupDish dtoToEntity(GroupDishDto groupDishDto) {
        GroupDish groupDish = new GroupDish();
        groupDish.setId(groupDishDto.getId());
        groupDish.setTitle(groupDishDto.getTitle());
        groupDish.setCreatedAt(groupDishDto.getCreatedAt());
        groupDish.setUpdatedAt(groupDishDto.getUpdatedAt());
        return groupDish;
    }

    public GroupDishDto entityToDto(GroupDish groupDish) {
        GroupDishDto groupDishDto = new GroupDishDto();
        groupDishDto.setId(groupDish.getId());
        groupDishDto.setTitle(groupDish.getTitle());
        groupDishDto.setCreatedAt(groupDish.getCreatedAt());
        groupDishDto.setUpdatedAt(groupDish.getUpdatedAt());
        return groupDishDto;
    }
}