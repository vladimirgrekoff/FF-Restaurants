package ru.findFood.rest.converters;

import org.springframework.stereotype.Component;
import ru.findFood.rest.dtos.RestaurantRequestItemDto;
import ru.findFood.rest.entities.RestaurantRequestItem;


@Component
public class RestaurantRequestItemConverter {




    public RestaurantRequestItemDto entityToDto(RestaurantRequestItem rRqItem) {
        return new RestaurantRequestItemDto(
                rRqItem.getId(),
                rRqItem.getRestaurantRequest().getId(),
                rRqItem.getDishId(),
                rRqItem.getTitle(),
                rRqItem.getDescription(),
                rRqItem.getGroupDishTitle(),
                rRqItem.getCalories(),
                rRqItem.getProteins(),
                rRqItem.getFats(),
                rRqItem.getCarbohydrates(),
                rRqItem.getHealthy(),
                rRqItem.getApproved(),
                rRqItem.getVerified()
        );
    }
}
