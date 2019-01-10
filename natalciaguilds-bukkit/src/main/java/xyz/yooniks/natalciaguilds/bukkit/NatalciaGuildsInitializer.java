package xyz.yooniks.natalciaguilds.bukkit;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import xyz.yooniks.natalciaguilds.bukkit.initializer.Initializer;
import xyz.yooniks.natalciaguilds.bukkit.initializer.impl.CommandInitializer;
import xyz.yooniks.natalciaguilds.bukkit.initializer.impl.ConfigInitializer;
import xyz.yooniks.natalciaguilds.bukkit.initializer.impl.GuildInitializer;
import xyz.yooniks.natalciaguilds.bukkit.initializer.impl.ListenerInitializer;
import xyz.yooniks.natalciaguilds.bukkit.initializer.impl.TablistInitializer;

class NatalciaGuildsInitializer implements Initializer {

  private final Logger logger;
  private final List<Initializer> initializers;

  NatalciaGuildsInitializer(NatalciaGuildsPlugin plugin) {
    this.initializers = Arrays.asList(
        new CommandInitializer(plugin.getGuildCommandManager()),
        new GuildInitializer(plugin.getGuildManager()),
        new ListenerInitializer(plugin),
        new ConfigInitializer(plugin),
        new TablistInitializer(plugin)
    );
    this.logger = plugin.getLogger();
  }

  @Override
  public void initialize() {
    this.initializers.forEach(initializer -> {
      long time = System.currentTimeMillis();
      initializer.initialize();
      time = System.currentTimeMillis() - time;

      this.logger.info(
          String
              .format("%s made his task in: %d ms", initializer.getClass().getSimpleName(), time));
    });
  }

}
