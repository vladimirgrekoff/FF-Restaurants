package ru.findFood.rest.converters;

import org.springframework.stereotype.Component;
import ru.findFood.rest.dtos.MailBoxItemDto;
import ru.findFood.rest.models.MailBoxItem;


@Component
public class MailBoxItemConverter {

    public MailBoxItem dtoToEntity(MailBoxItemDto mBIDto) {
        return new MailBoxItem(
                mBIDto.getDishId(),
                mBIDto.getDishTitle(),
                mBIDto.getDishDescription(),
                mBIDto.getDishGroupDishTitle(),
                mBIDto.getDishCalories(),
                mBIDto.getDishProteins(),
                mBIDto.getDishFats(),
                mBIDto.getDishCarbohydrates(),
                mBIDto.getDishHealthy(),
                mBIDto.getDishApproved());
    }


    public MailBoxItemDto entityToDto(MailBoxItem mBItem) {
        return new MailBoxItemDto(
                mBItem.getDishId(),
                mBItem.getDishTitle(),
                mBItem.getDishDescription(),
                mBItem.getDishGroupDishTitle(),
                mBItem.getDishCalories(),
                mBItem.getDishProteins(),
                mBItem.getDishFats(),
                mBItem.getDishCarbohydrates(),
                mBItem.getDishHealthy(),
                mBItem.getDishApproved());
    }
}
