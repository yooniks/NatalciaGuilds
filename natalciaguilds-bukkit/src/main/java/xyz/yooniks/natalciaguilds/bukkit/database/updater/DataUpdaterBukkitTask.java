package xyz.yooniks.natalciaguilds.bukkit.database.updater;

import org.bukkit.Bukkit;
import xyz.yooniks.natalciaguilds.bukkit.NatalciaGuildsPlugin;

public class DataUpdaterBukkitTask implements DataUpdater {

  @Override
  public void execute(Runnable runnable) {
    Bukkit.getScheduler().runTaskAsynchronously(NatalciaGuildsPlugin.getInstance(), runnable);
  }

}
