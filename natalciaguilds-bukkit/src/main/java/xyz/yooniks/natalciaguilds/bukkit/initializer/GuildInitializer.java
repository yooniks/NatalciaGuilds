package xyz.yooniks.natalciaguilds.bukkit.initializer;

import xyz.yooniks.natalciaguilds.api.guild.GuildManager;
import xyz.yooniks.natalciaguilds.bukkit.database.concurrency.TaskExecutor;
import xyz.yooniks.natalciaguilds.bukkit.database.concurrency.TaskExecutorBukkit;
import xyz.yooniks.natalciaguilds.bukkit.database.data.guild.GuildFlatDataManager;

public class GuildInitializer implements Initializer {

  private final GuildManager guildManager;

  public GuildInitializer(GuildManager guildManager) {
    this.guildManager = guildManager;
  }

  @Override
  public void initialize() {
    final TaskExecutor taskExecutor = new TaskExecutorBukkit();
    this.guildManager.setDatabaseManager(new GuildFlatDataManager(taskExecutor));
    this.guildManager.addGuilds(this.guildManager.getDatabaseManager().findAll());
  }

}
