package ru.findFood.rest.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailBoxItem {
    private Long dishId;
    private String dishTitle;
    private String dishDescription;
    private String dishGroupDishTitle;
    private Integer dishCalories;
    private Integer dishProteins;
    private Integer dishFats;
    private Integer dishCarbohydrates;
    private Boolean dishHealthy;
    private Boolean dishApproved;
}

