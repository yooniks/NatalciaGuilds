package xyz.yooniks.natalciaguilds.bukkit.initializer.impl;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import xyz.yooniks.natalciaguilds.bukkit.element.tablist.Tablist;
import xyz.yooniks.natalciaguilds.bukkit.element.tablist.TablistRunnable;
import xyz.yooniks.natalciaguilds.bukkit.element.tablist.impl.ProtocolLibTablist;
import xyz.yooniks.natalciaguilds.bukkit.helper.MessageHelper;
import xyz.yooniks.natalciaguilds.bukkit.initializer.Initializer;

public class TablistInitializer implements Initializer {

  private final Plugin plugin;

  public TablistInitializer(Plugin plugin) {
    this.plugin = plugin;
  }

  @Override
  public void initialize() {
    final FileConfiguration config = this.plugin.getConfig();
    for (int slot = 0; slot < 80; slot++) {
      config.addDefault("tablist.element.slot." + slot, " ");
    }
    config.addDefault("tablist.element.footer", "&6Footer");
    config.addDefault("tablist.element.header", "&6Header");

    final Tablist tablist = new ProtocolLibTablist(
        MessageHelper.colored(config.getString("tablist.element.header")),
        MessageHelper.colored(config.getString("tablist.element.footer")),
        elementsFromSection(config.getConfigurationSection("tablist.element.slot")));

    this.plugin.getLogger()
        .info("Using " + tablist.getClass().getSimpleName() + " as tablist updater");

    this.plugin.getServer().getScheduler().runTaskTimerAsynchronously(
        this.plugin, new TablistRunnable(tablist), 20L, 20L);
  }

  private Map<Integer, String> elementsFromSection(ConfigurationSection section) {
    final Map<Integer, String> elements = new HashMap<>();
    for (String key : section.getKeys(false)) {
      final int slot = Integer.parseInt(key);
      final String value = section.getString("tablist.element.slot." + slot, " ");
      elements.put(slot, MessageHelper.colored(value));
    }
    return elements;
  }

}
