package xyz.yooniks.natalciaguilds.bukkit.hook;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.yooniks.natalciaguilds.api.ranking.Ranking;
import xyz.yooniks.natalciaguilds.api.user.User;
import xyz.yooniks.natalciaguilds.bukkit.NatalciaGuildsPlugin;

public class PlaceHolderAPIHook implements PluginHook {

  private final String identifier = "natalciaguilds";

  @Override
  public boolean shouldBeLoaded() {
    return Bukkit.getPluginManager().getPlugin("PlaceHolderAPI") != null;
  }

  @Override
  public void initialize() {
    new GuildPlaceholderExpansion(NatalciaGuildsPlugin.getInstance()).register();
  }

  @Override
  public void unload() {
    PlaceholderAPI.unregisterPlaceholderHook(this.identifier);
  }

  private class GuildPlaceholderExpansion extends PlaceholderExpansion {

    private final NatalciaGuildsPlugin plugin;

    private GuildPlaceholderExpansion(NatalciaGuildsPlugin plugin) {
      this.plugin = plugin;
    }

    @Override
    public String getIdentifier() {
      return PlaceHolderAPIHook.this.identifier;
    }

    @Override
    public String getPlugin() {
      return this.plugin.getName();
    }

    @Override
    public String getAuthor() {
      return String.join(",", this.plugin.getDescription().getAuthors());
    }

    @Override
    public String getVersion() {
      return this.plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {

      if (player == null) {
        return "";
      }

      final User user = this.plugin.getUserManager().createUser(player.getUniqueId());

      final Ranking ranking = user.getRanking();
      if (identifier.equalsIgnoreCase("points")) {
        return String.valueOf(ranking.getPoints());
      } else if (identifier.equalsIgnoreCase("kills")) {
        return String.valueOf(ranking.getKills());
      } else if (identifier.equalsIgnoreCase("deaths")) {
        return String.valueOf(ranking.getDeaths());
      } else if (identifier.equalsIgnoreCase("kd")) {
        return String.valueOf(ranking.calculateKDRatio());
      }
      return "";
    }

  }

}
