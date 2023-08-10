package ru.findFood.rest.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.findFood.rest.entities.Dish;
import ru.findFood.rest.models.MailBox;

import java.util.UUID;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class MailBoxService {

    private final DishesService dishesService;
    private final @Qualifier("redisOnServiceTemplate") RedisTemplate<String, Object> redisOnServiceTemplate;



    public String generateMailBoxUuid() {
        return UUID.randomUUID().toString();
    }

    public MailBox getCurrentMailBox(String mailBoxId) {
        if (!redisOnServiceTemplate.hasKey(mailBoxId)) {
            MailBox mailBox = new MailBox();
            redisOnServiceTemplate.opsForValue().set(mailBoxId, mailBox);
        }
        return (MailBox) redisOnServiceTemplate.opsForValue().get(mailBoxId);
    }

    public void addToMailBox(String mailBoxId, Long dishId) {
        execute(mailBoxId, mailBox -> {
            Dish d = dishesService.findById(dishId);
            mailBox.add(d);
        });
    }

    public void deleteFromMailBox(String mailBoxId, Long dishId) {
        execute(mailBoxId, mailBox -> mailBox.remove(dishId));
    }
    public void clearMailBox(String mailBoxId) {
        execute(mailBoxId, MailBox::clear);
    }

    private void execute(String mailBoxId, Consumer<MailBox> action) {
        MailBox mailBox = getCurrentMailBox(mailBoxId);
        action.accept(mailBox);
        redisOnServiceTemplate.opsForValue().set(mailBoxId, mailBox);
    }

}
