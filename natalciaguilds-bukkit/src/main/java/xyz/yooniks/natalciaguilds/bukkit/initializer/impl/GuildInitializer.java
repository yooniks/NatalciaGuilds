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
  public void initialize() {
    this.guildManager.setDatabaseManager(new GuildFlatDataManager(null));
    this.guildManager.addGuilds(this.guildManager.getDatabaseManager().findAll());
  }

}
