package ru.findFood.rest.embeddedRedis.ports;

import java.io.IOException;
import java.net.ServerSocket;
import redis.embedded.PortProvider;
import redis.embedded.exceptions.RedisBuildingException;

public class EphemeralPortProvider implements PortProvider {
  @Override
  public int next() {
    try {
      final ServerSocket socket = new ServerSocket(0);
      socket.setReuseAddress(false);
      int port = socket.getLocalPort();
      socket.close();
      return port;
    } catch (IOException e) {
      // не должно когда-либо произойти
      throw new RedisBuildingException("Не удалось предоставить временный порт", e);
    }
  }
}
