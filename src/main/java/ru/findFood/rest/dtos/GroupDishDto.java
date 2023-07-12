package ru.findFood.rest.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ru.findFood.rest.entities.Dish;

import java.time.LocalDateTime;
import java.util.List;


@Data
@RequiredArgsConstructor
@Schema(description = "Модель группы блюд")
public class GroupDishDto {

    @Schema(description = "ID группы блюд",  requiredMode = Schema.RequiredMode.AUTO, example = "4")
    Long id;

    @Schema(description = "Имя группы блюд",  requiredMode = Schema.RequiredMode.AUTO, example = "Супы")
    private String title;

//    @Schema(description = "Блюда", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "список")
//    private List<Dish> dishes;

    @Schema(description = "Дата добавления",  requiredMode = Schema.RequiredMode.AUTO)
    private LocalDateTime createdAt;

    @Schema(description = "Дата последнего обновления",  requiredMode = Schema.RequiredMode.AUTO)
    private LocalDateTime updatedAt;

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

//    public List<Dish> getDishes() {
//        return dishes;
//    }
//
//    public void setDishes(List<Dish> dishes) {
//        this.dishes = dishes;
//    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
