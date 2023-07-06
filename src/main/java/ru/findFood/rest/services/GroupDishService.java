package ru.findFood.rest.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.findFood.rest.entities.Dish;
import ru.findFood.rest.entities.GroupDish;
import ru.findFood.rest.repositories.GroupDishRepository;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class GroupDishService {
    private final GroupDishRepository groupDishRepository;

    public Optional<GroupDish> findByTitle(String title) {
        return groupDishRepository.findByTitle(title);
    }
}
