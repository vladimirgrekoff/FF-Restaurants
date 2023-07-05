package ru.findFood.rest.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.findFood.rest.dtos.DishDto;
import ru.findFood.rest.entities.Dish;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Schema(description = "Модель группы блюд")
public class GroupDishDto {
    @Schema(description = "ID группы блюд",  requiredMode = Schema.RequiredMode.AUTO, example = "4")
    Long id;

    @Schema(description = "Имя группы блюд",  requiredMode = Schema.RequiredMode.AUTO, example = "Супы")
    private String title;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public GroupDishDto() {
    }


    public GroupDishDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
