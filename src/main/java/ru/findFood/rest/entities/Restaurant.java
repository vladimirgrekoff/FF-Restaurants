package ru.findFood.rest.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "restaurants")
@Data
@NoArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @OneToOne
    @JoinColumn(name="restaurant_info_id")
    private RestaurantInfo restaurantInfo;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Restaurant(Long id) {
        this.id = id;
    }

    public Restaurant(String title, LocalDateTime createdAt) {
        this.title = title;
        this.createdAt = createdAt;
    }

    public Restaurant(String title, RestaurantInfo restaurantInfo, LocalDateTime createdAt) {
        this.title = title;
        this.restaurantInfo = restaurantInfo;
        this.createdAt = createdAt;
    }
}
