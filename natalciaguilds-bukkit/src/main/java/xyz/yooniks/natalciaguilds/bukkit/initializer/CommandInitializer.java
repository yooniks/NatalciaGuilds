package xyz.yooniks.natalciaguilds.bukkit.initializer;

import java.util.List;
import java.util.stream.Collectors;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import xyz.yooniks.natalciaguilds.bukkit.NatalciaGuildsPlugin;
import xyz.yooniks.natalciaguilds.bukkit.command.GuildCommandManager;
import xyz.yooniks.natalciaguilds.bukkit.command.GuildCreateCommandArgument;
import xyz.yooniks.natalciaguilds.bukkit.command.GuildPermissionCommandArgument;

public class CommandInitializer implements Initializer {

  private final NatalciaGuildsPlugin plugin;

  public CommandInitializer(NatalciaGuildsPlugin plugin) {
    this.plugin = plugin;
  }

  @Override
  public void initialize() {
    final GuildCommandManager commandManager = this.plugin.getGuildCommandManager();

    final FileConfiguration config = this.plugin.getConfig();

    final List<ItemStack> requiredItems = config
        .getConfigurationSection("items").getKeys(false)
        .stream()
        .map(itemId -> config.getItemStack("items." + itemId))
        .collect(Collectors.toList());
    commandManager.addGuildCommandArgument(new GuildCreateCommandArgument(
        requiredItems, this.plugin.getUserManager(), this.plugin.getGuildManager()));

    commandManager
        .addGuildCommandArgument(new GuildPermissionCommandArgument(this.plugin.getUserManager()));
  }

}
