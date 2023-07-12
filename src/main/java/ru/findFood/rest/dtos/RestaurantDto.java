package ru.findFood.rest.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.RequiredArgsConstructor;


import java.time.LocalDateTime;
import java.util.List;

@Data
@RequiredArgsConstructor
@Schema(description = "Ресторан")
public class RestaurantDto {

    @Schema(description = "ID ресторана", requiredMode = Schema.RequiredMode.AUTO, example = "1")
    private Long id;

    @Schema(description = "Название",  requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 255, minLength = 1, example = "Стейкхаус")
    private String title;

    @Schema(description = "Информация о ресторане",  requiredMode = Schema.RequiredMode.REQUIRED, example = "Информация о ресторане Стейкхаус: описание, контакты, часы работы")
    private Long restaurant_info_id;

//    @Schema(description = "Блюда ресторана",  requiredMode = Schema.RequiredMode.REQUIRED, example = "Список блюд ресторана")
//    private List<DishDto> dishesList;


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

    public Long getRestaurant_info_id() {
        return restaurant_info_id;
    }

    public void setRestaurant_info_id(Long restaurant_info_id) {
        this.restaurant_info_id = restaurant_info_id;
    }

//    public List<DishDto> getDishesList() {
//        return dishesList;
//    }
//
//    public void setDishesList(List<DishDto> dishesList) {
//        this.dishesList = dishesList;
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
