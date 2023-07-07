package ru.findFood.rest.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.findFood.rest.converters.CategoryConverter;
import ru.findFood.rest.dtos.CategoryDto;
import ru.findFood.rest.entities.Category;
import ru.findFood.rest.exceptions.ResourceNotFoundException;
import ru.findFood.rest.repositories.CategoryRepository;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    public CategoryDto findById(Long id){
        return categoryConverter.entityToDto(categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Категория с ID " + id + " не найдена")));
    }

    public CategoryDto findByTitle(String title) {
        return categoryConverter.entityToDto(categoryRepository.findByTitle(title).orElseThrow(() -> new ResourceNotFoundException("Категория с названием "+ title + " не найдена")));
    }

    public List<CategoryDto> findAllCategories(){

        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        for (Category c: categoryList) {
            categoryDtoList.add(categoryConverter.entityToDto(c));
        }
        return categoryDtoList;
    }
}
