package ru.findFood.rest.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.findFood.rest.converters.GroupDishDtoConverter;
import ru.findFood.rest.dtos.GroupDishDto;
import ru.findFood.rest.entities.GroupDish;
import ru.findFood.rest.exceptions.ResourceNotFoundException;
import ru.findFood.rest.repositories.GroupDishRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupDishService {
    private final GroupDishRepository groupDishRepository;
    private final GroupDishDtoConverter groupDishDtoConverter;

    public GroupDishDto findById(Long id){
        return groupDishDtoConverter.entityToDto(groupDishRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Группа блюд с ID " + id + " не найдена")));
    }

    public GroupDishDto findByTitle(String title) {
        return groupDishDtoConverter.entityToDto(groupDishRepository.findByTitle(title).orElseThrow(() -> new ResourceNotFoundException("Группа блюд с названием " + title +" не найдена")));
    }

    public List<GroupDishDto> findAllGroupDishes(){
        List<GroupDish> groupDishList = groupDishRepository.findAll();
        List<GroupDishDto> groupDishDtoList = new ArrayList<>();
        for (GroupDish gd: groupDishList) {
            groupDishDtoList.add(groupDishDtoConverter.entityToDto(gd));
        }
        return groupDishDtoList;
    }

    public GroupDishDto createNewGroupDish(GroupDishDto groupDishDto){
        GroupDish groupDish = groupDishDtoConverter.dtoToEntity(groupDishDto);
        groupDishRepository.save(groupDish);
        groupDishDto.setId(groupDish.getId());
        return groupDishDto;
    }

    public GroupDishDto updateGroupDish(GroupDishDto groupDishDto){
        GroupDish groupDish = groupDishRepository.findById(groupDishDto.getId()).orElseThrow(()-> new ResourceNotFoundException("Группа блюд с ID "+ groupDishDto.getId() + " не найдена"));
        if(groupDish != null){
            groupDish = groupDishDtoConverter.dtoToEntity(groupDishDto);
            groupDishRepository.save(groupDish);
        }
        return groupDishDto;
    }

    public void deleteGroupDishById(Long id){
        groupDishRepository.deleteById(id);
    }
}
