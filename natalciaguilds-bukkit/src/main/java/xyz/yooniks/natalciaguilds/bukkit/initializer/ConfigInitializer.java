package xyz.yooniks.natalciaguilds.bukkit.initializer;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.plugin.Plugin;
import xyz.yooniks.natalciaguilds.bukkit.config.MessagesConfig;
import xyz.yooniks.natalciaguilds.bukkit.config.SettingsConfig;
import xyz.yooniks.natalciaguilds.bukkit.config.system.Config;
import xyz.yooniks.natalciaguilds.bukkit.config.system.ConfigHelper;

public class ConfigInitializer implements Initializer {

  private final Plugin plugin;

  public ConfigInitializer(Plugin plugin) {
    this.plugin = plugin;
  }

  @Override
  public void initialize() {
    final Map<Class<? extends Config>, File> configs = new HashMap<>();
    final File messages = new File(plugin.getDataFolder(), "messages.yml");
    configs.put(MessagesConfig.class, messages);

    final File settings = new File(plugin.getDataFolder(), "settings.yml");
    configs.put(SettingsConfig.class, settings);

    this.registerConfigs(configs);
  }

  private void registerConfigs(Map<Class<? extends Config>, File> configs) {
    configs.forEach((clazz, file) -> ConfigHelper.create(file, clazz));
  }

}
