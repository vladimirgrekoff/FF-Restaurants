package ru.findFood.rest.embeddedRedis;

import java.util.List;
import ru.findFood.rest.embeddedRedis.exceptions.EmbeddedRedisException;

public interface Redis {
  boolean isActive();

  void start() throws EmbeddedRedisException;

  void stop() throws EmbeddedRedisException;

  List<Integer> ports();
}
