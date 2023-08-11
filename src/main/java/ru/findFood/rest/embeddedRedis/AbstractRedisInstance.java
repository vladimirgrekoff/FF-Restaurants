package ru.findFood.rest.embeddedRedis;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.findFood.rest.embeddedRedis.Redis;
import ru.findFood.rest.embeddedRedis.exceptions.EmbeddedRedisException;

abstract class AbstractRedisInstance implements Redis {
  protected List<String> args = Collections.emptyList();
  private volatile boolean active = false;
  private Process redisProcess;
  private final int port;

  private ExecutorService executor;

  protected AbstractRedisInstance(int port) {
    this.port = port;
  }

  @Override
  public boolean isActive() {
    return active;
  }

  @Override
  public synchronized void start() throws EmbeddedRedisException {
    if (active) {
      throw new EmbeddedRedisException("Этот экземпляр сервера Redis уже запущен...");
    }
    try {
      redisProcess = createRedisProcessBuilder().start();
      logErrors();
      awaitRedisServerReady();
      active = true;
    } catch (IOException e) {
      throw new EmbeddedRedisException("Ошибка запуска экземпляра Redis.", e);
    }
  }

  private void logErrors() {
    final InputStream errorStream = redisProcess.getErrorStream();
    BufferedReader reader = new BufferedReader(new InputStreamReader(errorStream));
    Runnable printReaderTask = new PrintReaderRunnable(reader);
    executor = Executors.newSingleThreadExecutor();
    executor.submit(printReaderTask);
  }

  private void awaitRedisServerReady() throws IOException {
    BufferedReader reader =
        new BufferedReader(new InputStreamReader(redisProcess.getInputStream()));
    try {
      String outputLine;
      do {
        outputLine = reader.readLine();
        if (outputLine == null) {
          // Что-то идет не так. Поток завершен до того, как сервер был активирован.
          throw new RuntimeException("Не могу запустить Redis-сервер. Подробности смотрите в журналах.");
        }
      } while (!outputLine.matches(redisReadyPattern()));
    } finally {
      closeQuietly(reader);
    }
  }

  protected abstract String redisReadyPattern();

  private ProcessBuilder createRedisProcessBuilder() {
    File executable = new File(args.get(0));
    ProcessBuilder pb = new ProcessBuilder(args);
    pb.directory(executable.getParentFile());
    return pb;
  }

  @Override
  public synchronized void stop() throws EmbeddedRedisException {
    if (active) {
      if (executor != null && !executor.isShutdown()) {
        executor.shutdown();
      }
      redisProcess.destroy();
      tryWaitFor();
      active = false;
    }
  }

  private void tryWaitFor() {
    try {
      redisProcess.waitFor();
    } catch (InterruptedException e) {
      throw new EmbeddedRedisException("Не удалось остановить экземпляр Redis", e);
    }
  }

  @Override
  public List<Integer> ports() {
    return Arrays.asList(port);
  }

  private static void closeQuietly(Reader reader) {
    if (reader == null) {
      return;
    }

    try {
      reader.close();
    } catch (Throwable ignored) {
      // ignore
    }
  }

  private static class PrintReaderRunnable implements Runnable {
    private final BufferedReader reader;

    private PrintReaderRunnable(BufferedReader reader) {
      this.reader = reader;
    }

    public void run() {
      try {
        readLines();
      } finally {
        closeQuietly(reader);
      }
    }

    public void readLines() {
      try {
        String line;
        while ((line = reader.readLine()) != null) {
          System.out.println(line);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
