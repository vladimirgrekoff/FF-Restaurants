package ru.findFood.rest.embeddedRedis.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import ru.findFood.rest.embeddedRedis.Redis;

public class JedisUtil {
  public static Set<String> jedisHosts(Redis redis) {
    final List<Integer> ports = redis.ports();
    return portsToJedisHosts(ports);
  }

  public static Set<String> portsToJedisHosts(List<Integer> ports) {
    Set<String> hosts = new HashSet<String>();
    for (Integer p : ports) {
      hosts.add("localhost:" + p);
    }
    return hosts;
  }
}
