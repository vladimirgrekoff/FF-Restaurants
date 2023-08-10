package ru.findFood.rest.models;

import lombok.Data;
import ru.findFood.rest.entities.Dish;

import java.util.ArrayList;
import java.util.List;


@Data
public class MailBox {
    private List<MailBoxItem> items;

    public MailBox(){
        this.items = new ArrayList<>();
    }

    public List<MailBoxItem> getItems() {
        return items;
    }

    public void add(Dish d) {
        if(items != null) {
            for (MailBoxItem item : items) {
                if (item.getDishId().equals(d.getId())) {
                    return;
                }
            }
        }
        MailBoxItem mailBoxItem = new MailBoxItem(
                d.getId(),
                d.getTitle(),
                d.getDescription(),
                d.getGroupDish().getTitle(),
                d.getCalories(),
                d.getProteins(),
                d.getFats(),
                d.getCarbohydrates(),
                d.getHealthy(),
                d.getApproved()
        );
        items.add(mailBoxItem);
    }
    public void remove(Long id) {
        for (MailBoxItem item : items) {
            if (item.getDishId().equals(id)) {
                items.remove(item);
                return;
            }
        }
    }

    public boolean isPresentInMailBox(Long id) {
        boolean result = false;
        for (MailBoxItem item : items) {
            if(item.getDishId() == id) {
                result = true;
            }
        }
        return result;
    }
    public void clear() {
        items.clear();
    }


}
