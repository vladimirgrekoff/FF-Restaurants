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

import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "dishes")
@Getter
@Setter
@NoArgsConstructor

public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "healthy", nullable = false)
    private Boolean healthy;

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

    @Column(name = "approved")
    private Boolean isApproved;

    @Column(name = "healthy")
    private Boolean isHealthy;

//завели категории отдельно, но хочу потестить и этот вариант тоже с алгоритмом
/*    @Column(name = "breakfast", nullable = false)
    private Boolean isBreakfast;

    @Column(name = "lunch", nullable = false)
    private Boolean isLunch;

    @Column(name = "dinner", nullable = false)
    private Boolean isDinner;

    @Column(name = "snack", nullable = false)
    private Boolean isSnack;*/


    //изменено (временно или постоянно решим)!!!!!!!!!!!!!!
//    @ManyToOne
    @OneToOne
    @JoinColumn(name = "group_dish_id")
    private GroupDish groupDish;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private List<Category> categories;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    //минимальный конструктор на всякий случай
    public Dish(String title) {
        this.title = title;
    }

    //конструктор с обязательными полями
    public Dish(String title, Long restaurant, Integer calories, Integer proteins, Integer fats, Integer carbohydrates, LocalDateTime createdAt) {
        this.title = title;
        this.restaurant_id = restaurant;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.createdAt = createdAt;
    }

    //почти полный конструктор
    //изменено (временно или постоянно решим)!!!!!!!!!!!!!!
    public Dish(String title, Long restaurant_id, String description, BigDecimal price, byte[] image, Integer calories, Integer proteins, Integer fats, Integer carbohydrates, GroupDish groupDish) {
        this.title = title;
        this.restaurant_id = restaurant_id;
        this.description = description;
        this.price = price;
        this.image = image;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.groupDish = groupDish;
        this.categories = categories;
        this.createdAt = createdAt;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return title.equals(dish.title) && restaurant_id.equals(dish.restaurant_id) && calories.equals(dish.calories) && proteins.equals(dish.proteins) && fats.equals(dish.fats) && carbohydrates.equals(dish.carbohydrates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, restaurant_id, calories, proteins, fats, carbohydrates, createdAt);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", restaurant=" + restaurant_id.toString() +
=======
        this.approved =  approved;
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
                ", isApproved=" + isApproved +
                ", isHealthy=" + isHealthy +
              //  ", groupDish=" + groupDish.toString()+
              //  ", categories=" + categories.toString() +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
