package xyz.yooniks.natalciaguilds.bukkit.initializer;

import java.util.Arrays;
import org.bukkit.event.Listener;
import xyz.yooniks.natalciaguilds.bukkit.NatalciaGuildsPlugin;
import xyz.yooniks.natalciaguilds.bukkit.listener.PlayerLoadSaveListener;

public class ListenerInitializer implements Initializer {

  private final NatalciaGuildsPlugin plugin;

  public ListenerInitializer(NatalciaGuildsPlugin plugin) {
    this.plugin = plugin;
  }

  @Override
  public void initialize() {
    this.registerListeners(new PlayerLoadSaveListener(this.plugin.getUserManager()));
  }

  private void registerListeners(Listener... listeners) {
    Arrays.stream(listeners)
        .forEach(listener ->
            this.plugin.getServer().getPluginManager().registerEvents(listener, plugin));
  }

}
