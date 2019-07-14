package xyz.yooniks.natalciaguilds.bukkit.database.concurrency;

import org.bukkit.Bukkit;
import xyz.yooniks.natalciaguilds.bukkit.NatalciaGuildsPlugin;

public class TaskExecutorBukkit implements TaskExecutor {

  @Override
  public void execute(Runnable runnable) {
    Bukkit.getScheduler().runTaskAsynchronously(NatalciaGuildsPlugin.getInstance(), runnable);
  }

}
