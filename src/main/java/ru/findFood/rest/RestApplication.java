package ru.findFood.rest;

import jakarta.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.findFood.rest.embeddedRedis.RedisServer;

import java.io.IOException;

@SpringBootApplication
public class RestApplication {
	private static RedisServer redisServer;

	@PreDestroy
	private static void preDestroy(){
		redisServer.stop();
	}

	private static void embeddedServerInit() throws IOException {
		redisServer = new RedisServer(6379);
		redisServer.start();

	}

	public static void main(String[] args) throws IOException {

		SpringApplication.run(RestApplication.class, args);

		embeddedServerInit();
	}

}
