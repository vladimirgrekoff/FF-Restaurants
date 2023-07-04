package ru.findFood.rest.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "dishes")
@Getter
@Setter
@NoArgsConstructor
@Data
public class Dish {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "nutritionist")
    private Boolean nutritionist_approved;

    //заводим диетологам ID в таблице ресторанов, и они под ним смогут пополнять базу блюд
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "restaurant_id", nullable = false)
//    private Restaurant restaurant;
    //TODO добавить импорт для класса Restaurant
    //TODO добавить связь @OneToMany(mappedBy = "restaurant") в класс Restaurant

    //изменено (временно или постоянно решим)!!!!!!!!!!!!!!
    @Column(name = "restaurant_id", nullable = false)
    private Long restaurant_id;

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
//    @Column(name = "breakfast", nullable = false)
//    private Boolean isBreakfast;
//
//    @Column(name = "lunch", nullable = false)
//    private Boolean isLunch;
//
//    @Column(name = "dinner", nullable = false)
//    private Boolean isDinner;
//
//    @Column(name = "snack", nullable = false)
//    private Boolean isSnack;

    //изменено (временно или постоянно решим)!!!!!!!!!!!!!!
    @ManyToOne
    @JoinColumn(name = "group_dish_id")
    private GroupDish groupDish;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;



    //конструктор со всеми non-nullable полями БД
//    public Dish(String title, Long restaurant_id, Integer calories, Integer proteins, Integer fats, Integer carbohydrates, Boolean isBreakfast, Boolean isLunch, Boolean isDinner, Boolean isSnack) {
//        this.title = title;
//        this.restaurant_id = restaurant_id;
//        this.calories = calories;
//        this.proteins = proteins;
//        this.fats = fats;
//        this.carbohydrates = carbohydrates;
//        this.isBreakfast = isBreakfast;
//        this.isLunch = isLunch;
//        this.isDinner = isDinner;
//        this.isSnack = isSnack;
//    }

    //изменено (временно или постоянно решим)!!!!!!!!!!!!!!
    public Dish(Long id, String title, Long restaurant_id, String description, Boolean nutritionist_approved, BigDecimal price, byte[] image, Integer calories, Integer proteins, Integer fats, Integer carbohydrates, GroupDish groupDish) {
        this.id = id;
        this.title = title;
        this.restaurant_id = restaurant_id;
        this.description = description;
        this.nutritionist_approved = nutritionist_approved;
        this.price = price;
        this.image = image;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.groupDish = groupDish;
    }

    //минимальный конструктор на всякий случай
    public Dish(String title) {
        this.title = title;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Dish dish = (Dish) o;
//        return title.equals(dish.title) && Objects.equals(restaurant, dish.restaurant) && Objects.equals(calories, dish.calories) && Objects.equals(proteins, dish.proteins) && Objects.equals(fats, dish.fats) && Objects.equals(carbohydrates, dish.carbohydrates);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(title, restaurant, calories, proteins, fats, carbohydrates);
//    }

//    @Override
//    public String toString() {
//        return "Dish{" +
//                "title='" + title + '\'' +
//                ", description='" + description + '\'' +
//                ", price=" + price +
//                ", calories=" + calories +
//                ", proteins=" + proteins +
//                ", fats=" + fats +
//                ", carbohydrates=" + carbohydrates +
//                ", isBreakfast=" + isBreakfast +
//                ", isLunch=" + isLunch +
//                ", isDinner=" + isDinner +
//                ", isSnack=" + isSnack +
//                '}';
//    }

    //изменено (временно или постоянно решим)!!!!!!!!!!!!!!
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
                ", group dish=" + groupDish +
                '}';
    }
}
