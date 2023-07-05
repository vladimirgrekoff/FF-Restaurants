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

//    @OneToMany(mappedBy = "groupDish")
//    @Schema(description = "Список продуктов группы", requiredMode = Schema.RequiredMode.REQUIRED)
//    private Collection<DishDto> dishes;

//    private List<Dish> dishes;
//
//    @Column(name = "created_at")
//    @CreationTimestamp
//    private LocalDateTime createdAt;
//
//    @Column(name = "updated_at")
//    @UpdateTimestamp
//    private LocalDateTime updatedAt;

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

//    public Collection<DishDto> getDishes() {
//        return dishes;
//    }
//
//    public void setDishes(Collection<DishDto> dishes) {
//        this.dishes = dishes;
//    }

    public GroupDishDto() {
    }

//    public GroupDishDto(Long id, String title, Collection<DishDto> dishes) {
    public GroupDishDto(Long id, String title) {
        this.id = id;
        this.title = title;
//        this.dishes = dishes;
    }
}
