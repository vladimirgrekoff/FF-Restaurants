package ru.findFood.rest.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.findFood.rest.converters.GroupDishConverter;
import ru.findFood.rest.dtos.GroupDishDto;
import ru.findFood.rest.entities.Dish;
import ru.findFood.rest.entities.GroupDish;
import ru.findFood.rest.exceptions.ResourceAlreadyInUseException;
import ru.findFood.rest.exceptions.ResourceNotFoundException;
import ru.findFood.rest.repositories.GroupDishRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupDishService {
    private final GroupDishRepository groupDishRepository;

    public GroupDish findById(Long id){
        Optional<GroupDish > groupDish = groupDishRepository.findById(id);
        if(groupDish.isPresent()){
            return groupDish.get();
        } else {
            throw new ResourceNotFoundException("Группа блюд с ID " + id + " не найдена");
        }
    }

    public GroupDish findByTitle(String title) {
        Optional<GroupDish> groupDish = groupDishRepository.findByTitle(title);
        if(groupDish.isPresent()){
            return groupDish.get();
        } else {
            throw new ResourceNotFoundException("Группа блюд с названием " + title +" не найдена");
        }
    }

    public List<GroupDish> findAll(){
        return groupDishRepository.findAll();
    }

    public void createNewGroupDish(GroupDish groupDish){
        if (groupDishRepository.findByTitle(groupDish.getTitle()).isPresent()){
            throw new ResourceAlreadyInUseException("Название группы блюд: '" + groupDish.getTitle() + "' уже используется");
        } else {
            groupDishRepository.save(groupDish);
        }
    }
    @Transactional
    public void updateGroupDish(GroupDish groupDish){
        if (groupDish.getId() != null) {
            groupDishRepository.findById(groupDish.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Группа блюд с id: " + groupDish.getId() + " не найдена"));

            groupDishRepository.save(groupDish);
        } else {
            throw new ResourceNotFoundException("Группа блюд с id: " + groupDish.getId() + " не найдена");
        }
    }

    public void deleteGroupDishById(Long id){
        groupDishRepository.deleteById(id);
    }
}
