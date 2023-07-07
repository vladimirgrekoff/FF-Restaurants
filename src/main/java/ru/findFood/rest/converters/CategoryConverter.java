package ru.findFood.rest.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.findFood.rest.dtos.CategoryDto;
import ru.findFood.rest.entities.Category;

@Component
@RequiredArgsConstructor
public class CategoryConverter {

    public Category dtoToEntity (CategoryDto categoryDto){
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setTitle(categoryDto.getTitle());
        category.setCreatedAt(categoryDto.getCreatedAt());
        category.setUpdatedAt(categoryDto.getUpdatedAt());
        return category;
    }

    public CategoryDto entityToDto (Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setCreatedAt(category.getCreatedAt());
        categoryDto.setUpdatedAt(category.getUpdatedAt());
        return categoryDto;
    }
}
