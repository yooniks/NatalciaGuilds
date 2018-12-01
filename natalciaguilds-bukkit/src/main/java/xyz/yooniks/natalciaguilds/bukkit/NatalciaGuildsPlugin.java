package xyz.yooniks.natalciaguilds.bukkit;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.yooniks.natalciaguilds.api.NatalciaGuilds;
import xyz.yooniks.natalciaguilds.api.guild.GuildManager;
import xyz.yooniks.natalciaguilds.bukkit.command.basic.GuildCommandManager;
import xyz.yooniks.natalciaguilds.bukkit.command.basic.impl.GuildCommandManagerImpl;
import xyz.yooniks.natalciaguilds.bukkit.guild.GuildManagerImpl;
import xyz.yooniks.natalciaguilds.bukkit.helper.URIHelper;

public final class NatalciaGuildsPlugin extends JavaPlugin implements NatalciaGuilds {

  private GuildManager guildManager;
  private GuildCommandManager guildCommandManager;

  @Override
  public void onEnable() {
    this.printVersion();

    this.guildManager = new GuildManagerImpl();
    this.guildCommandManager = new GuildCommandManagerImpl();

    this.getLogger().info("Loading plugin.. ");

    final NatalciaGuildsInitializer initializer = new NatalciaGuildsInitializer(this);
    final long time = initializer.initialize();

    this.getLogger().info("Loaded plugin in " + time + "ms!");

  }

  @Override
  public void onDisable() {
  }

  @Override
  public GuildManager getGuildManager() {
    return guildManager;
  }

  public GuildCommandManager getGuildCommandManager() {
    return guildCommandManager;
  }

  private void printVersion() {
    try {
      final String availableVersion = URIHelper.readContent(new URI(URIHelper.VERSION_URL));
      final String currentVersion = this.getDescription().getVersion();
      if (availableVersion.equalsIgnoreCase(currentVersion)) {
        this.getLogger().info("Your server uses the newest version! =)");
      }
      else {
        this.getLogger().warning(String.format(
            "Your server uses old version.. "
                + "Available version: %s, current version: %s, download the newest version at %s",
            availableVersion, currentVersion, URIHelper.RELEASES_URL)
        );
      }
    }
    catch (IOException | URISyntaxException ex) {
      ex.printStackTrace();
    }
  }

  public static NatalciaGuildsPlugin getInstance() {
    return NatalciaGuildsPlugin.getPlugin(NatalciaGuildsPlugin.class);
  }

}
