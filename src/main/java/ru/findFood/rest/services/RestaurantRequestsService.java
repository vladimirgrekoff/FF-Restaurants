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
import ru.findFood.rest.models.MailBox;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantRequestsService {
    private final RestaurantRequestsRepository restaurantRequestsRepository;
    private final MailBoxService mailBoxService;
    private final RestaurantService restaurantService;
//    private final AuthServiceIntegration authServiceIntegration;

    public List<RestaurantRequest> findRestaurantRequests(String restaurant_name) {
        return restaurantRequestsRepository.findAllByRestaurantName(restaurant_name);
    }

    @Transactional
    public void createRequest(String username, String restMailBoxId) {
        Restaurant restaurant = restaurantService.findByByEmail(username);
        MailBox mailBox = mailBoxService.getCurrentMailBox(restMailBoxId);
        RestaurantRequest restaurantRequest = new RestaurantRequest();

        restaurantRequest.setRestaurantName(restaurant.getTitle());
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
        mailBoxService.clearMailBox(restMailBoxId);
    }

    @Transactional
    public void updateRequest(UpdateRequestItemDto updateRequestItemDto) {
        boolean findItem = false;
        boolean findDish = false;

        RestaurantRequest restaurantRequest = restaurantRequestsRepository.findById(updateRequestItemDto.getRequestId())
                .orElseThrow(() -> new ResourceNotFoundException("Запрос с id: " + updateRequestItemDto.getRequestId() + "не найден"));


        List<RestaurantRequestItem> restaurantRequestItemList = restaurantRequest.getRestaurantRequestItems();
        for (RestaurantRequestItem ri : restaurantRequestItemList) {
            if (ri.getId() == updateRequestItemDto.getId()) {
                findItem = true;
                if (ri.getRestaurantRequest().getId() == updateRequestItemDto.getRequestId()) {
                    if (ri.getDishId() == updateRequestItemDto.getDishId()) {
                        findDish = true;
                        ri.setHealthy(updateRequestItemDto.getDishHealthy());
                        ri.setApproved(updateRequestItemDto.getDishApproved());
                        ri.setVerified(updateRequestItemDto.getVerified());
                    }
                }
            }
        }

        if (!findItem) {
            throw new ResourceNotFoundException("Запись с ID: " + updateRequestItemDto.getId() + " не найдена");
        }
        if (!findDish) {
            throw new ResourceNotFoundException("Блюдо с ID: " + updateRequestItemDto.getDishId() + " не найдено");
        }

        restaurantRequest.setRestaurantRequestItems(restaurantRequestItemList);
        restaurantRequestsRepository.save(restaurantRequest);
    }

}