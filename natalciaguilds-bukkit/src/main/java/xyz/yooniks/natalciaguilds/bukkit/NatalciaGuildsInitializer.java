package xyz.yooniks.natalciaguilds.bukkit;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import jdk.internal.reflect.Reflection;
import org.bukkit.event.Listener;
import org.reflections.Reflections;
import xyz.yooniks.natalciaguilds.api.guild.GuildManager;
import xyz.yooniks.natalciaguilds.bukkit.command.GuildCommand;
import xyz.yooniks.natalciaguilds.bukkit.command.basic.GuildCommandArgumentExecutor;
import xyz.yooniks.natalciaguilds.bukkit.command.basic.GuildCommandManager;
import xyz.yooniks.natalciaguilds.bukkit.command.basic.impl.GuildCreateCommandArgument;
import xyz.yooniks.natalciaguilds.bukkit.database.manager.guild.GuildFlatDataManager;
import xyz.yooniks.natalciaguilds.bukkit.listener.PlayerJoinListener;

class NatalciaGuildsInitializer {

  private final NatalciaGuildsPlugin plugin;

  NatalciaGuildsInitializer(NatalciaGuildsPlugin plugin) {
    this.plugin = plugin;
  }

  void initialize() {
    this.initGuildManager();
    this.initCommands();
    this.registerListeners(new PlayerJoinListener());
  }

  private void initGuildManager() {
    final GuildManager guildManager = this.plugin.getGuildManager();
    //set database manager and load guilds
    guildManager.setDatabaseManager(new GuildFlatDataManager());
    guildManager.addGuilds(guildManager.getDatabaseManager().findAll());
  }

  private void initCommands() {
    final GuildCommandManager commandManager = this.plugin.getGuildCommandManager();
    //main command, others are inside main command as arguments
    commandManager.setCommand(new GuildCommand(commandManager));

    //init commands in package xyz.yooniks...command.basic.impl
    final Reflections reflections = new Reflections("xyz.yooniks.natalciaguilds.bukkit.command.basic.impl");
    reflections.getSubTypesOf(GuildCommandArgumentExecutor.class)
        .forEach(commandManager::addArgument);
  }

  private void registerListeners(Listener... listeners) {
    Arrays.stream(listeners).forEach(
        listener -> this.plugin.getServer().getPluginManager().registerEvents(listener, plugin)
    );
  }

}
