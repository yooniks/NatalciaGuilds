package xyz.yooniks.natalciaguilds.bukkit.initializer;

import xyz.yooniks.natalciaguilds.api.guild.GuildManager;
import xyz.yooniks.natalciaguilds.bukkit.database.manager.guild.GuildFlatDataManager;
import xyz.yooniks.natalciaguilds.bukkit.database.updater.DataUpdater;
import xyz.yooniks.natalciaguilds.bukkit.database.updater.DataUpdaterBukkitTask;

public class GuildInitializer implements Initializer {

  private final GuildManager guildManager;

  public GuildInitializer(GuildManager guildManager) {
    this.guildManager = guildManager;
  }

  @Override
  public void initialize() {
    final DataUpdater dataUpdater = new DataUpdaterBukkitTask();
    this.guildManager.setDatabaseManager(new GuildFlatDataManager(dataUpdater));
    this.guildManager.addGuilds(this.guildManager.getDatabaseManager().findAll());
  }

}
