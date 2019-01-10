package xyz.yooniks.natalciaguilds.bukkit.database.updater;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import xyz.yooniks.natalciaguilds.bukkit.config.SettingsConfig;

public class DataUpdaterWithOwnQueue implements DataUpdater {

  private final ExecutorService executorService = Executors.newFixedThreadPool(
      SettingsConfig.CONCURRENCY$THREADS);

  @Override
  public void execute(Runnable runnable) {
    this.executorService.submit(runnable);
  }

}
