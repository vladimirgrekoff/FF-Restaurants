package ru.findFood.rest.embeddedRedis.exceptions;

public class EmbeddedRedisException extends RuntimeException {
  public EmbeddedRedisException(String message, Throwable cause) {
    super(message, cause);
  }

  public EmbeddedRedisException(String message) {
    super(message);
  }
}
