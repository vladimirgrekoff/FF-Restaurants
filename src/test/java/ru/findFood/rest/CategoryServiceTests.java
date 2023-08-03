package ru.findFood.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.findFood.rest.entities.Category;
import ru.findFood.rest.exceptions.ResourceNotFoundException;
import ru.findFood.rest.services.CategoryService;
import ru.findFood.rest.utils.Constants;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CategoryServiceTests {
    @Autowired
    private CategoryService categoryService;

    @Test
/*    public void findAllTest(){
        Category mockCategory = new Category("1");
        categoryService.createNewCategory(mockCategory);
        Category mockCategory1 = new Category("2");
        categoryService.createNewCategory(mockCategory1);
        Category mockCategory2 = new Category("3");
        categoryService.createNewCategory(mockCategory2);
        List<Category> correctCategoryList = Arrays.asList(mockCategory, mockCategory1, mockCategory2);

        assertEquals(correctCategoryList, categoryService.findAll());
    }*/
    public void findAllTest(){
        List<Category> allCategories = categoryService.findAll();

        assertEquals(Constants.INMEMORYDATABASECATEGORYCOUNT, allCategories.size());
    }

    @Test
    public void findByIdTest(){
        Category testCategory01 = new Category("Ланч");
        categoryService.createNewCategory(testCategory01);
        Category testCategory02 = new Category("Полдник");
        categoryService.createNewCategory(testCategory02);
        Long testCategory02Id = testCategory02.getId();

        assertEquals(testCategory02Id, categoryService.findById(testCategory02Id).getId());
        assertEquals("Полдник", categoryService.findById(testCategory02Id).getTitle());

        categoryService.deleteCategoryById(testCategory01.getId());
        categoryService.deleteCategoryById(testCategory02Id);
    }

    @Test
    public void findByTitleTest(){
        Category testCategory03 = new Category("Ночной перекус");
        categoryService.createNewCategory(testCategory03);
        Category testCategory04 = new Category("Первый завтрак");
        categoryService.createNewCategory(testCategory04);
        Long testCategory04Id = testCategory04.getId();

        assertEquals("Ночной перекус", categoryService.findByTitle("Ночной перекус").getTitle());
        assertEquals(testCategory04Id, categoryService.findByTitle("Первый завтрак").getId());

        categoryService.deleteCategoryById(testCategory03.getId());
        categoryService.deleteCategoryById(testCategory04Id);
    }

    @Test
    public void createNewCategoryTest(){
        Category testCategory05 = new Category("Второй завтрак");
        categoryService.createNewCategory(testCategory05);
        Long testCategory05Id = testCategory05.getId();

        assertEquals("Второй завтрак", categoryService.findById(testCategory05Id).getTitle());

        categoryService.deleteCategoryById(testCategory05Id);
    }

    @Test
    public void updateCategoryTest(){
        Category testCategory06 = new Category("Новая категория");
        categoryService.createNewCategory(testCategory06);
        Long testCategory06Id = testCategory06.getId();
        testCategory06.setTitle("Чаепитие");
        categoryService.updateCategory(testCategory06);

        assertEquals("Чаепитие", categoryService.findById(testCategory06Id).getTitle());

        categoryService.deleteCategoryById(testCategory06Id);
    }

    @Test
    public void deleteCategoryByIdTest(){
        Category testCategory07 = new Category("Застолье");
        categoryService.createNewCategory(testCategory07);
        Long testCategory07Id = testCategory07.getId();
        categoryService.deleteCategoryById(testCategory07Id);

        assertThrows(ResourceNotFoundException.class, () -> categoryService.findById(testCategory07Id));
    }
}
