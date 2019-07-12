package xyz.yooniks.natalciaguilds.bukkit.initializer;

import java.io.File;
import java.util.Arrays;
import org.bukkit.plugin.Plugin;
import xyz.yooniks.natalciaguilds.bukkit.config.MessagesConfig;
import xyz.yooniks.natalciaguilds.bukkit.config.SettingsConfig;
import xyz.yooniks.natalciaguilds.bukkit.config.system.Config;
import xyz.yooniks.natalciaguilds.bukkit.config.system.ConfigManager;

public class ConfigInitializer implements Initializer {

  private final Plugin plugin;
  private final ConfigManager configManager;

  public ConfigInitializer(Plugin plugin, ConfigManager configManager) {
    this.plugin = plugin;
    this.configManager = configManager;
  }

  @Override
  public void initialize() {
    final File messages = new File(this.plugin.getDataFolder(), "messages.yml");
    final File settings = new File(this.plugin.getDataFolder(), "settings.yml");

    this.initConfigs(new SimpleConfig(MessagesConfig.class, messages),
        new SimpleConfig(SettingsConfig.class, settings));
  }

  private void initConfigs(SimpleConfig... configs) {
    Arrays.stream(configs)
        .map(config -> new Config(config.configFile, config.configClass))
        .forEach(this.configManager::reload);
  }

  private final class SimpleConfig {

    private final Class<? extends Config> configClass;
    private final File configFile;

    private SimpleConfig(
        Class<? extends Config> configClass, File configFile) {
      this.configClass = configClass;
      this.configFile = configFile;
    }
  }

}
