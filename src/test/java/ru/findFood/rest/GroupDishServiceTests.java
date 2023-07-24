package ru.findFood.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.findFood.rest.entities.GroupDish;
import ru.findFood.rest.exceptions.ResourceNotFoundException;
import ru.findFood.rest.services.GroupDishService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class GroupDishServiceTests {
    @Autowired
    private GroupDishService groupDishService;

    //@Test
    public void findAllTest(){
        GroupDish mockGroupDish = new GroupDish("1");
        groupDishService.createNewGroupDish(mockGroupDish);
        GroupDish mockGroupDish1 = new GroupDish("2");
        groupDishService.createNewGroupDish(mockGroupDish1);
        GroupDish mockGroupDish2 = new GroupDish("3");
        groupDishService.createNewGroupDish(mockGroupDish2);
        List<GroupDish> correctGroupDishList = Arrays.asList(mockGroupDish, mockGroupDish1, mockGroupDish2);

        assertEquals(correctGroupDishList, groupDishService.findAll());
    }

    @Test
    public void findByIdTest(){
        GroupDish testGroupDish01 = new GroupDish("Сладкое");
        groupDishService.createNewGroupDish(testGroupDish01);
        GroupDish testGroupDish02 = new GroupDish("Хлеб");
        groupDishService.createNewGroupDish(testGroupDish02);
        Long testGroupDish02Id = testGroupDish02.getId();

        assertEquals(testGroupDish02Id, groupDishService.findById(testGroupDish02Id).getId());
        assertEquals("Хлеб", groupDishService.findById(testGroupDish02Id).getTitle());
    }

    @Test
    public void findByTitleTest(){
        GroupDish testGroupDish03 = new GroupDish("Чипсы");
        groupDishService.createNewGroupDish(testGroupDish03);
        Long testGroupDish03Id = testGroupDish03.getId();
        GroupDish testGroupDish04 = new GroupDish("Сладости");
        groupDishService.createNewGroupDish(testGroupDish04);

        assertEquals(testGroupDish03Id, groupDishService.findByTitle("Чипсы").getId());
        assertEquals("Сладости", groupDishService.findByTitle("Сладости").getTitle());

    }

    @Test
    public void createNewGroupDishTest(){
        GroupDish testGroupDish05 = new GroupDish("Морепродукты");
        groupDishService.createNewGroupDish(testGroupDish05);
        Long testGroupDish05Id = testGroupDish05.getId();

        assertEquals("Морепродукты", groupDishService.findById(testGroupDish05Id).getTitle());
    }

    @Test
    public void updateGroupDishTest(){
        GroupDish testGroupDish05 = new GroupDish("Полуфабрикаты");
        groupDishService.createNewGroupDish(testGroupDish05);
        Long testGroupDish05Id = testGroupDish05.getId();
        testGroupDish05.setTitle("Мясные блюда");
        groupDishService.updateGroupDish(testGroupDish05);

        assertEquals("Мясные блюда", groupDishService.findById(testGroupDish05Id).getTitle());
    }

    @Test
    public void deleteGroupDishByIdTest(){
        GroupDish testGroupDish05 = new GroupDish("Соки");
        groupDishService.createNewGroupDish(testGroupDish05);
        Long testGroupDish05Id = testGroupDish05.getId();
        groupDishService.deleteGroupDishById(testGroupDish05Id);

        assertThrows(ResourceNotFoundException.class, () -> groupDishService.findById(testGroupDish05Id));
    }
}
