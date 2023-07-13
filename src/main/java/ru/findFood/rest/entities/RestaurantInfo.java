package ru.findFood.rest.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "restaurant_infos")
@Data
@NoArgsConstructor
public class RestaurantInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(mappedBy = "restaurantInfo")
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(name = "description")
    private String description;

    @Column(name = "quisines")
    private String cuisines;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "open_hours")
    private String openHours;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;



    //полный конструктор без апдейта и id
    public RestaurantInfo(Restaurant restaurant, String description, String cuisines, String address, String phoneNumber, String email, String openHours, LocalDateTime createdAt) {
        this.restaurant = restaurant;
//        this.restaurantId = restaurant_id;
        this.description = description;
        this.cuisines = cuisines;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.openHours = openHours;
        this.createdAt = createdAt;
    }

    public RestaurantInfo(Restaurant restaurant, String description) {
        this.restaurant = restaurant;
        this.description = description;
    }
}
