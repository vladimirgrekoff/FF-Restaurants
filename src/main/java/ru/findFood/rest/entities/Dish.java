package ru.findFood.rest.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "dishes")
@Getter
@Setter
@NoArgsConstructor
public class Dish {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    //заводим диетологам ID в таблице ресторанов, и они под ним смогут пополнять базу блюд
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
//    private Restaurant restaurant;
    //TODO добавить импорт для класса Restaurant
    //TODO добавить связь @OneToMany(mappedBy = "restaurant") в класс Restaurant

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "image_data")
    private byte[] image;

    @Column(name = "calories", nullable = false)
    private Integer calories;

    @Column(name = "proteins", nullable = false)
    private Integer proteins;

    @Column(name = "fats", nullable = false)
    private Integer fats;

    @Column(name = "carbohydrates", nullable = false)
    private Integer carbohydrates;

    //возможно, не лучшее решение. Пока что мне кажется, так проще, чем заводить отдельную сущность Категорий для этого параметра. Можем рассмотреть вариант с коллекцией.
    @Column(name = "breakfast", nullable = false)
    private Boolean isBreakfast;

    @Column(name = "lunch", nullable = false)
    private Boolean isLunch;

    @Column(name = "dinner", nullable = false)
    private Boolean isDinner;

    @Column(name = "snack", nullable = false)
    private Boolean isSnack;


    //конструктор со всеми non-nullable полями БД
    public Dish(String title, Restaurant restaurant, Integer calories, Integer proteins, Integer fats, Integer carbohydrates, Boolean isBreakfast, Boolean isLunch, Boolean isDinner, Boolean isSnack) {
        this.title = title;
        this.restaurant = restaurant;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.isBreakfast = isBreakfast;
        this.isLunch = isLunch;
        this.isDinner = isDinner;
        this.isSnack = isSnack;
    }

    //минимальный конструктор на всякий случай
    public Dish(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return title.equals(dish.title) && Objects.equals(restaurant, dish.restaurant) && Objects.equals(calories, dish.calories) && Objects.equals(proteins, dish.proteins) && Objects.equals(fats, dish.fats) && Objects.equals(carbohydrates, dish.carbohydrates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, restaurant, calories, proteins, fats, carbohydrates);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", calories=" + calories +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", carbohydrates=" + carbohydrates +
                ", isBreakfast=" + isBreakfast +
                ", isLunch=" + isLunch +
                ", isDinner=" + isDinner +
                ", isSnack=" + isSnack +
                '}';
    }
}
