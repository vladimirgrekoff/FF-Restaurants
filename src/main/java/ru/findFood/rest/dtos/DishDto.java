package ru.findFood.rest.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.Column;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.findFood.rest.entities.GroupDish;
import ru.findFood.rest.entities.Restaurant;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Модель блюда")
public class DishDto {

    @Schema(description = "ID блюда", requiredMode = Schema.RequiredMode.AUTO, example = "13")
    private Long id;

    @Schema(description = "Наименование блюда",  requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 255, minLength = 3, example = "Борщ")
    private String title;

    @Schema(description = "Одобрено диетологом",  requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 255, minLength = 3, example = "true")
    private Boolean healthy;

    @Schema(description = "Ресторан",  requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Restaurant restaurant;

    @Schema(description = "Описание блюда",  requiredMode = Schema.RequiredMode.REQUIRED, maxLength = 255, minLength = 3, example = "Борщ с говядиной, ломтиком сала на хлебном чипсе и сметаной")
    private String description;

    @Schema(description = "Цена блюда",  requiredMode = Schema.RequiredMode.REQUIRED, example = "360.00")
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

    @Schema(description = "К/Б/Ж/У подтверждаю",  requiredMode = Schema.RequiredMode.REQUIRED, example = "true")
    private Boolean approved;

    @Schema(description = "Группа блюда",  requiredMode = Schema.RequiredMode.REQUIRED, example = "Супы")
//    private String groupDishDto.getTitle;
    private GroupDishDto groupDishDto;

    @Schema(description = "дата внесения",  requiredMode = Schema.RequiredMode.AUTO)
    private LocalDateTime createdAt;

    @Schema(description = "дата последнего обновления",  requiredMode = Schema.RequiredMode.AUTO)
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

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
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

    public Boolean getHealthy() {
        return healthy;
    }

    public void setHealthy(Boolean healthy) {
        this.healthy = healthy;
    }




    public GroupDishDto getGroupDishDto() {
        return groupDishDto;
    }

    public void setGroupDishDto(GroupDishDto groupDishDto) {
        this.groupDishDto = groupDishDto;
    }



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



    public DishDto() {
    }

    public DishDto(Long id, String title, Boolean healthy, Restaurant restaurant, String description, BigDecimal price, byte[] image, Integer calories, Integer proteins, Integer fats, Integer carbohydrates, Boolean approved, GroupDishDto groupDishDto/*GroupDishDto*//* groupDishTitle*//*, *//**//*Long*//**//* GroupDishDto group_dish_id*/) {
        this.id = id;
        this.title = title;
        this.healthy = healthy;
        this.restaurant = restaurant;
        this.description = description;
        this.price = price;
        this.image = image;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.approved = approved;
        this.groupDishDto = groupDishDto;
    }
}
