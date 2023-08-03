package ru.findFood.rest.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "dishes")
@NoArgsConstructor
@Getter
@Setter
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

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
    private Boolean approved;

    @Column(name = "healthy")
    private Boolean healthy;

    @ManyToOne
    @JoinColumn(name = "group_dish_id")
    private GroupDish groupDish;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    @Column(name="used_last_time")
    private LocalDateTime usedLastTime;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;


    public Dish(String title, Integer calories, Integer proteins, Integer fats, Integer carbohydrates) {
        this.title = title;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.approved = false;
        this.healthy = false;
    }


    public Dish(String title, Restaurant restaurant, Integer calories, Integer proteins, Integer fats, Integer carbohydrates) {
        this.title = title;
        this.restaurant = restaurant;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.approved = false;
        this.healthy = false;
    }

    //почти полный конструктор
    public Dish(String title, Restaurant restaurant, String description, BigDecimal price, Integer calories, Integer proteins, Integer fats, Integer carbohydrates, Boolean healthy, Boolean approved, GroupDish groupDish, Category category) {
        this.title = title;
        this.restaurant = restaurant;
        this.description = description;
        this.price = price;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.healthy = healthy;
        this.approved = approved;
        this.groupDish = groupDish;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Objects.equals(id, dish.id) && title.equals(dish.title) && Objects.equals(restaurant, dish.restaurant) && Objects.equals(calories, dish.calories) && Objects.equals(proteins, dish.proteins) && Objects.equals(fats, dish.fats) && Objects.equals(carbohydrates, dish.carbohydrates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, restaurant, calories, proteins, fats, carbohydrates);
    }
}
