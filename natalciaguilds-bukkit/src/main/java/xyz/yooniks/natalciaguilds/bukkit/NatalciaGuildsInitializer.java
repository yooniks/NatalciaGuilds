package xyz.yooniks.natalciaguilds.bukkit;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import xyz.yooniks.natalciaguilds.bukkit.initializer.Initializer;

class NatalciaGuildsInitializer {

  private final Logger logger;
  private final Set<Initializer> initializers = new HashSet<>();

  NatalciaGuildsInitializer(Logger logger) {
    this.logger = logger;
  }

  public void initialize() {
    this.initializers.forEach(initializer -> {
      long time = System.currentTimeMillis();
      initializer.initialize();
      time = System.currentTimeMillis() - time;

      this.logger.info(String.format("%s made his task in: %d ms",
          initializer.getClass().getSimpleName(), time));
    });
  }

  void addInitializer(Initializer... initializers) {
    this.initializers.addAll(Arrays.asList(initializers));
  }

}
