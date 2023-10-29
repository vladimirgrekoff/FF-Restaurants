package ru.findFood.rest.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "restaurant_request_items")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRequestItem {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private RestaurantRequest restaurantRequest;

    @Column(name = "dish_id")
    private Long dishId;

    @Column(name = "dish_title")
    private String title;

    @Column(name = "dish_description")
    private String description;

    @Column(name = "group_dish_title")
    private String groupDishTitle;

    @Column(name = "calories")
    private Integer calories;

    @Column(name = "proteins")
    private Integer proteins;

    @Column(name = "fats")
    private Integer fats;

    @Column(name = "carbohydrates")
    private Integer carbohydrates;

    @Column(name = "healthy")
    private Boolean healthy;

    @Column(name = "approved")
    private Boolean approved;

    @Column(name = "lastname")
    private String lastname;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public RestaurantRequestItem(Long dishId, RestaurantRequest restaurantRequest, String title, String description, String groupDishTitle, Integer calories, Integer proteins, Integer fats, Integer carbohydrates, Boolean healthy, Boolean approved) {


        this.dishId = dishId;
        this.restaurantRequest = restaurantRequest;
        this.title = title;
        this.description = description;
        this.groupDishTitle = groupDishTitle;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.healthy = healthy;
        this.approved = approved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RestaurantRequestItem that = (RestaurantRequestItem) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
