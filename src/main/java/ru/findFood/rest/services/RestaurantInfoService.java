package ru.findFood.rest.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.findFood.rest.converters.RestaurantInfoConverter;
import ru.findFood.rest.dtos.RestaurantInfoDto;
import ru.findFood.rest.entities.RestaurantInfo;
import ru.findFood.rest.exceptions.ResourceNotFoundException;
import ru.findFood.rest.repositories.RestaurantInfoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantInfoService {
    private final RestaurantInfoRepository restaurantInfoRepository;
    private final RestaurantInfoConverter restaurantInfoConverter;

    public RestaurantInfoDto findById(Long id){
        return restaurantInfoConverter.entityToDto(restaurantInfoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Информация с ID " + id + " не найдена")));
    }

    public RestaurantInfoDto findByRestaurantId(Long id){

        return restaurantInfoConverter.entityToDto(restaurantInfoRepository.findByRestaurantId(id).orElseThrow(()-> new ResourceNotFoundException("Информация о ресторане с таким ID - " + id + " не найдена")));
    }

    public List<RestaurantInfoDto> findAll(){
        List<RestaurantInfo> restaurantInfoList = restaurantInfoRepository.findAll();
        List<RestaurantInfoDto> restaurantInfoDtoList = new ArrayList<>();
        for(RestaurantInfo ri: restaurantInfoList){
            restaurantInfoDtoList.add(restaurantInfoConverter.entityToDto(ri));
        }
        return restaurantInfoDtoList;
    }

    public RestaurantInfoDto createNewRestaurantInfo(RestaurantInfoDto restaurantInfoDto){
        RestaurantInfo restaurantInfo = restaurantInfoConverter.dtoToEntity(restaurantInfoDto);
        restaurantInfoRepository.save(restaurantInfo);
        restaurantInfoDto.setId(restaurantInfo.getId());
        return restaurantInfoDto;
    }

    public RestaurantInfoDto updateRestaurantInfo(RestaurantInfoDto restaurantInfoDto) {
        RestaurantInfo restaurantInfo = restaurantInfoRepository.findById(restaurantInfoDto.getId()).orElseThrow(()-> new ResourceNotFoundException("Информация о ресторане с ID " +restaurantInfoDto.getId()+ " не найдена"));
        if (restaurantInfo != null){
            restaurantInfo = restaurantInfoConverter.dtoToEntity(restaurantInfoDto);
            restaurantInfoRepository.save(restaurantInfo);
        }
        return restaurantInfoDto;
    }

    public void deleteRestaurantInfoById(Long id) {
        restaurantInfoRepository.deleteById(id);
    }
}
