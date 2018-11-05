package xyz.yooniks.natalciaguilds.bukkit;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.yooniks.natalciaguilds.NatalciaGuilds;
import xyz.yooniks.natalciaguilds.api.GuildManager;
import xyz.yooniks.natalciaguilds.bukkit.guild.GuildManagerImpl;

public final class NatalciaGuildsBukkit extends JavaPlugin implements NatalciaGuilds {

  private GuildManager guildManager;

  @Override
  public void onEnable() {
    this.guildManager = new GuildManagerImpl();
  }

  @Override
  public void onDisable() {
  }

  @Override
  public GuildManager getGuildManager() {
    return guildManager;
  }

}