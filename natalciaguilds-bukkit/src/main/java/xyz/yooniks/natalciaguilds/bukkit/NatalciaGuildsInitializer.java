package xyz.yooniks.natalciaguilds.bukkit;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import xyz.yooniks.natalciaguilds.bukkit.initializer.Initializer;
import xyz.yooniks.natalciaguilds.bukkit.initializer.impl.CommandInitializer;
import xyz.yooniks.natalciaguilds.bukkit.initializer.impl.GuildInitializer;

class NatalciaGuildsInitializer implements Initializer {

  private final Logger logger;
  private final List<Initializer> initializers;

  NatalciaGuildsInitializer(NatalciaGuildsPlugin plugin) {
    this.initializers = Arrays.asList(
        new CommandInitializer(plugin.getGuildCommandManager()),
        new GuildInitializer(plugin.getGuildManager())
    );
    this.logger = plugin.getLogger();
  }

  @Override
  public long initialize() {
    final long start = System.currentTimeMillis();

    this.initializers.forEach(initializer -> {
      final long time = initializer.initialize();
      logger.info(
          String.format("%s made his task in: %d", initializer.getClass().getSimpleName(), time));
    });

    return System.currentTimeMillis() - start;
  }

}
