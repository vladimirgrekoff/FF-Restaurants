package ru.findFood.rest.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
public class RedisConfig {
    @Bean(value = "jedisOnServiceConnectionFactory")
    public JedisConnectionFactory jedisOnServiceConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean(value = "redisOnServiceTemplate")
    public RedisTemplate<String, Object> redisOnServiceTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(jedisOnServiceConnectionFactory());
        return template;
    }
}
