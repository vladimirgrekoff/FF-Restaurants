package ru.findFood.rest.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.findFood.rest.converters.CategoryConverter;
import ru.findFood.rest.dtos.CategoryDto;
import ru.findFood.rest.entities.Category;
import ru.findFood.rest.entities.Dish;
import ru.findFood.rest.exceptions.ResourceAlreadyInUseException;
import ru.findFood.rest.exceptions.ResourceNotFoundException;
import ru.findFood.rest.repositories.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findById(Long id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            return category.get();
        } else {
            throw new ResourceNotFoundException("Категория с ID " + id + " не найдена");
        }
    }

    public Category findByTitle(String title) {
        Optional<Category> category = categoryRepository.findByTitle(title);
        if(category.isPresent()){
            return category.get();
        } else {
            throw new ResourceNotFoundException("Категория с названием "+ title + " не найдена");
        }
    }


    public void createNewCategory(Category category){
        if (categoryRepository.findByTitle(category.getTitle()).isPresent()){
            throw new ResourceAlreadyInUseException("Название категории блюд: '" + category.getTitle() + "' уже используется");
        } else {
            categoryRepository.save(category);
        }
    }

    public void updateCategory(Category category){
        if (category.getId() != null || category.getId() != 0) {
            Category categoryFound = categoryRepository.findById(category.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Категория с ID " + category.getId() + " не найдена"));
            if (categoryFound != null) {
                categoryRepository.save(category);
            }
        }else {
            throw new ResourceNotFoundException("Категория с ID " + category.getId() + " не найдена");
        }
    }

    public void deleteCategoryById(Long id){
        categoryRepository.deleteById(id);
    }
}
