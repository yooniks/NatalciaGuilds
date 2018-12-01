package xyz.yooniks.natalciaguilds.bukkit.initializer.impl;

import xyz.yooniks.natalciaguilds.api.guild.GuildManager;
import xyz.yooniks.natalciaguilds.bukkit.database.manager.guild.GuildFlatDataManager;
import xyz.yooniks.natalciaguilds.bukkit.initializer.Initializer;

public class GuildInitializer implements Initializer {

  private final GuildManager guildManager;

  public GuildInitializer(GuildManager guildManager) {
    this.guildManager = guildManager;
  }

  @Override
  public long initialize() {
    final long start = System.currentTimeMillis();

    this.guildManager.setDatabaseManager(new GuildFlatDataManager());
    this.guildManager.addGuilds(this.guildManager.getDatabaseManager().findAll());

    return System.currentTimeMillis() - start;
  }

}
