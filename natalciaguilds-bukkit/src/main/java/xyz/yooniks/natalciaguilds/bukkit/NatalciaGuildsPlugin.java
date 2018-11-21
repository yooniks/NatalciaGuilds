package xyz.yooniks.natalciaguilds.bukkit;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.yooniks.natalciaguilds.api.NatalciaGuilds;
import xyz.yooniks.natalciaguilds.api.guild.GuildManager;
import xyz.yooniks.natalciaguilds.bukkit.command.GuildCommand;
import xyz.yooniks.natalciaguilds.bukkit.command.argument.GuildCommandArgumentManager;
import xyz.yooniks.natalciaguilds.bukkit.command.argument.impl.GuildCommandArgumentManagerImpl;
import xyz.yooniks.natalciaguilds.bukkit.command.argument.impl.GuildCreateCommandArgument;
import xyz.yooniks.natalciaguilds.bukkit.database.manager.guild.GuildFlatDataManager;
import xyz.yooniks.natalciaguilds.bukkit.guild.GuildManagerImpl;
import xyz.yooniks.natalciaguilds.bukkit.helper.CommandHelper;
import xyz.yooniks.natalciaguilds.bukkit.helper.URIHelper;

public final class NatalciaGuildsPlugin extends JavaPlugin implements NatalciaGuilds {

  private GuildManager guildManager;
  private GuildCommandArgumentManager guildCommandArgumentManager;

  @Override
  public void onEnable() {
    this.guildManager = new GuildManagerImpl();
    //set database manager and load guilds
    this.guildManager.setDatabaseManager(new GuildFlatDataManager());
    this.guildManager.addGuilds(this.guildManager.getDatabaseManager().findAll());

    this.guildCommandArgumentManager = new GuildCommandArgumentManagerImpl();
    this.guildCommandArgumentManager.addArgument(new GuildCreateCommandArgument("create", "zaloz"));

    //main command, others are inside main command as arguments
    CommandHelper.registerCommand("g", new GuildCommand(
        this.guildCommandArgumentManager,
        "guild",
        "Glowna komenda od gildii",
        "/g",
        Arrays.asList("guilds", "gildie", "factions", "g", "funnyguilds"))
    );

    this.printVersion();
  }

  @Override
  public void onDisable() {
  }

  @Override
  public GuildManager getGuildManager() {
    return guildManager;
  }

  private void printVersion() {
    try {
      final String url = "https://github.com/yooniks/NatalciaGuilds";
      final String availableVersion = URIHelper.readContent(new URI(url + "/version.txt"));
      final String currentVersion = this.getDescription().getVersion();
      if (availableVersion.equalsIgnoreCase(currentVersion)) {
        this.getLogger().info("Your server uses the newest version! =)");
      }
      else {
        this.getLogger().warning(String.format(
            "Your server uses old version.. "
                + "Available version: %s, current version: %s, download the newest version at %s",
            availableVersion, currentVersion, url + "/releases")
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
