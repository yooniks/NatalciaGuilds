package xyz.yooniks.natalciaguilds.bukkit.initializer.impl;

import java.util.Arrays;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import xyz.yooniks.natalciaguilds.bukkit.initializer.Initializer;
import xyz.yooniks.natalciaguilds.bukkit.listener.PlayerJoinListener;

public class ListenerInitializer implements Initializer {

  private final Plugin plugin;

  public ListenerInitializer(Plugin plugin) {
    this.plugin = plugin;
  }

  @Override
  public long initialize() {
    final long start = System.currentTimeMillis();

    this.registerListeners(new PlayerJoinListener());

    return System.currentTimeMillis() - start;
  }

  private void registerListeners(Listener... listeners) {
    Arrays.stream(listeners).forEach(
        listener -> this.plugin.getServer().getPluginManager().registerEvents(listener, plugin)
    );
  }

}
