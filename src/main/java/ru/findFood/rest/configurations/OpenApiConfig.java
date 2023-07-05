package ru.findFood.rest.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    //http://localhost:8189/ff-restaurants/swagger-ui/index.html#
    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("FF-Restaurants - Сервис поиска блюд для составления оптимального меню на день")
                                .version("1")
                );
    }
}

