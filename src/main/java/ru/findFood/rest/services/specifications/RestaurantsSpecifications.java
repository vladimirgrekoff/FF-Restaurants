package ru.findFood.rest.services.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.findFood.rest.entities.Restaurant;

import java.util.Locale;

public class RestaurantsSpecifications {
    public static Specification<Restaurant> titleLike(String partTitle){
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.lower(root.get("title")), String.format("%%%s%%", partTitle.toLowerCase(Locale.ROOT))));
    }
}
