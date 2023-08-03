package ru.findFood.rest.services;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.findFood.rest.dtos.UpdateRequestItemDto;
import ru.findFood.rest.entities.Restaurant;
import ru.findFood.rest.entities.RestaurantRequest;
import ru.findFood.rest.entities.RestaurantRequestItem;
import ru.findFood.rest.exceptions.ResourceNotFoundException;
import ru.findFood.rest.repositories.RestaurantRequestsRepository;
import ru.findFood.rest.utils.MailBox;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantRequestsService {
    private final RestaurantRequestsRepository restaurantRequestsRepository;
    private final MailBoxService mailBoxService;
    private final RestaurantService restaurantService;
//    private final AuthServiceIntegration authServiceIntegration;

    public List<RestaurantRequest> findRestaurantRequests(String restaurant_title) {
        return restaurantRequestsRepository.findAllByRestaurantTitle(restaurant_title);
    }
    @Transactional
    public RestaurantRequest createRequest(String restaurant_title) {
        Restaurant restaurant = restaurantService.findByTitle(restaurant_title);
        MailBox mailBox = mailBoxService.getCurrentMailBox(restaurant.getTitle());
        RestaurantRequest restaurantRequest = new RestaurantRequest();

        restaurantRequest.setRestaurantTitle(restaurant_title);
        restaurantRequest.setRestaurantRequestItems(mailBox.getItems().stream().map(
                mailBoxItem -> new RestaurantRequestItem(
                                mailBoxItem.getDishId(),
                                restaurantRequest,
                                mailBoxItem.getDishTitle(),
                                mailBoxItem.getDishDescription(),
                                mailBoxItem.getDishGroupDishTitle(),
                                mailBoxItem.getDishCalories(),
                                mailBoxItem.getDishProteins(),
                                mailBoxItem.getDishFats(),
                                mailBoxItem.getDishCarbohydrates(),
                                mailBoxItem.getDishHealthy(),
                                mailBoxItem.getDishApproved()
                )
        ).collect(Collectors.toList()));
        restaurantRequestsRepository.save(restaurantRequest);
        mailBoxService.clearMailBox(restaurant_title);
        return restaurantRequest;
    }

    @Transactional
    public void updateRequest(UpdateRequestItemDto updateRequestItemDto) {
        boolean findItem = false;
        boolean findDish = false;

        RestaurantRequest restaurantRequest = restaurantRequestsRepository.findById(updateRequestItemDto.getRequestId())
                .orElseThrow(() -> new ResourceNotFoundException("Запрос с id: " + updateRequestItemDto.getRequestId() + "не найден"));


        List<RestaurantRequestItem> restaurantRequestItemList = restaurantRequest.getRestaurantRequestItems();
        for (RestaurantRequestItem ri:restaurantRequestItemList) {
            if(Objects.equals(ri.getId(), updateRequestItemDto.getId())){
                findItem = true;
                if(Objects.equals(ri.getRestaurantRequest().getId(), updateRequestItemDto.getRequestId())) {
                    if(Objects.equals(ri.getDishId(), updateRequestItemDto.getDishId())) {
                        findDish = true;
                        ri.setHealthy(updateRequestItemDto.getDishHealthy());
                        ri.setApproved(updateRequestItemDto.getDishApproved());
                        ri.setVerified(updateRequestItemDto.getVerified());
                    }
                }
            }
        }

        if (!findItem){
            throw new ResourceNotFoundException("Запись с ID: " + updateRequestItemDto.getId() + " не найдена");
        }
        if (!findDish) {
            throw new ResourceNotFoundException("Блюдо с ID: " + updateRequestItemDto.getDishId() + " не найдено");
        }

        restaurantRequest.setRestaurantRequestItems(restaurantRequestItemList);
        restaurantRequestsRepository.save(restaurantRequest);
    }

}