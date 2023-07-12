package ru.findFood.rest.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "restaurants")
@Data
@RequiredArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @OneToOne(cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
    @JoinColumn(name = "restaurant_info_id")
    private RestaurantInfo restaurantInfo;

//    @OneToMany(mappedBy = "restaurant")
//    private List<Dish> dishes;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;




    public Restaurant(String title, LocalDateTime createdAt) {
        this.title = title;
        this.createdAt = createdAt;
    }

    public Restaurant(String title, RestaurantInfo restaurantInfo, /*List<Dish> dishes,*/LocalDateTime createdAt) {
        this.title = title;
        this.restaurantInfo = restaurantInfo;
//        this.dishes = dishes;
        this.createdAt = createdAt;
    }
}
