package ru.findFood.rest;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.findFood.rest.converters.DishConverter;
import ru.findFood.rest.converters.GroupDishDtoConverter;
import ru.findFood.rest.dtos.DishDto;
import ru.findFood.rest.dtos.GroupDishDto;
import org.springframework.boot.test.context.SpringBootTest;
import ru.findFood.rest.entities.GroupDish;
import ru.findFood.rest.repositories.DishesRepository;
import ru.findFood.rest.services.DishesService;
import ru.findFood.rest.services.GroupDishService;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

@SpringBootTest(classes = DishesService.class)
public class DishesServiceTests {
    @Autowired
    private DishesService dishesService;
    @MockBean
    private DishesRepository dishesRepository;
    @MockBean
    private GroupDishService groupDishService;
    @MockBean
    private GroupDishDtoConverter groupDishDtoConverter;
    @MockBean
    private DishConverter dishConverter;

    @Test
    public void createNewDishesTest() {
        GroupDish groupDish = new GroupDish();
        groupDish.setId(8L);
        groupDish.setTitle("Фкукты");
        GroupDishDto groupDishDto = groupDishDtoConverter.entityToDto(groupDish);

        Mockito.doReturn(Optional.of(groupDish))
                .when(groupDishService)
                .findByTitle("Фкукты");

        DishDto dishDto = new DishDto();

        dishesService.createNewDish(dishDto);

        Mockito.verify(dishesRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }
}
