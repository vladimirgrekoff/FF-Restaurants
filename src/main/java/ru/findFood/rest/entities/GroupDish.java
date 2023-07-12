package ru.findFood.rest.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "group_dishes")
@NoArgsConstructor
@Data
public class GroupDish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "title")
    private String title;

  //  @OneToMany(mappedBy = "groupDish")
  //  @Column(name = "groupDish")
   // private List<Dish> dishes;
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;


//    public List<Dish> getDishes() {
//        return dishes;
//    }
//
//    public void setDishes(List<Dish> dishes) {
//        this.dishes = dishes;
//    }

    public GroupDish(String title, LocalDateTime createdAt) {
        this.title = title;
        this.createdAt = createdAt;
    }
}
