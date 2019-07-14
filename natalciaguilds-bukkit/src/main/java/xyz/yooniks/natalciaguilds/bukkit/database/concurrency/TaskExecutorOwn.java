package xyz.yooniks.natalciaguilds.bukkit.database.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import xyz.yooniks.natalciaguilds.bukkit.config.SettingsConfig;

public class TaskExecutorOwn implements TaskExecutor {

  private final ExecutorService executorService = Executors.newFixedThreadPool(
      SettingsConfig.CONCURRENCY$THREADS);

  @Override
  public void execute(Runnable runnable) {
    this.executorService.submit(runnable);
  }

}
