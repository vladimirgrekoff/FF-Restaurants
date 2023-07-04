package ru.findFood.rest.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.Column;
import ru.findFood.rest.entities.GroupDish;
import java.math.BigDecimal;

@Schema(description = "Модель блюда")
public class DishDto {

    @Schema(description = "ID блюда", requiredMode = Schema.RequiredMode.AUTO, example = "13")
    private Long id;

    @Schema(description = "Наименование блюда",  requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 255, minLength = 3, example = "Суп")
    private String title;

    @Schema(description = "Одобрено диетологом",  requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 255, minLength = 3, example = "true")
    private Boolean nutritionist_approved;

    @Schema(description = "ID ресторана",  requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long restaurant_id;

    @Schema(description = "Описание блюда",  requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 255, minLength = 3, example = "Суп")
    private String description;

    @Schema(description = "Цена блюда",  requiredMode = Schema.RequiredMode.REQUIRED, example = "100.00")
    private BigDecimal price;

    @Schema(description = "Ссылка на фото",  requiredMode = Schema.RequiredMode.REQUIRED, example = "")
    private byte[] image;

    @Schema(description = "Калории",  requiredMode = Schema.RequiredMode.REQUIRED, example = "")
    private Integer calories;

    @Schema(description = "Белки",  requiredMode = Schema.RequiredMode.REQUIRED, example = "")
    private Integer proteins;

    @Schema(description = "Жиры",  requiredMode = Schema.RequiredMode.REQUIRED, example = "")
    private Integer fats;

    @Schema(description = "Углеводы",  requiredMode = Schema.RequiredMode.REQUIRED, example = "")
    private Integer carbohydrates;

    @Schema(description = "Группа блюда",  requiredMode = Schema.RequiredMode.REQUIRED, example = "Супы")
    private GroupDish group_dish_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getNutritionist_approved() {
        return nutritionist_approved;
    }

    public void setNutritionist_approved(Boolean nutritionist_approved) {
        this.nutritionist_approved = nutritionist_approved;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(Long restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getProteins() {
        return proteins;
    }

    public void setProteins(Integer proteins) {
        this.proteins = proteins;
    }

    public Integer getFats() {
        return fats;
    }

    public void setFats(Integer fats) {
        this.fats = fats;
    }

    public Integer getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Integer carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public GroupDish getGroup_dish_id() {
        return group_dish_id;
    }

    public void setGroup_dish_id(GroupDish group_dish_id) {
        this.group_dish_id = group_dish_id;
    }

    public DishDto() {
    }

    public DishDto(Long id, String title, Boolean nutritionist_approved, Long restaurant_id, String description, BigDecimal price, byte[] image, Integer calories, Integer proteins, Integer fats, Integer carbohydrates, GroupDish group_dish_id) {
        this.id = id;
        this.title = title;
        this.nutritionist_approved = nutritionist_approved;
        this.restaurant_id = restaurant_id;
        this.description = description;
        this.price = price;
        this.image = image;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.group_dish_id = group_dish_id;
    }
}
