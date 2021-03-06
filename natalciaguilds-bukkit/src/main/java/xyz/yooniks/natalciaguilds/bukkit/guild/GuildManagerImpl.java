package xyz.yooniks.natalciaguilds.bukkit.guild;

import com.google.common.collect.ImmutableList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import org.bukkit.Location;
import xyz.yooniks.natalciaguilds.api.database.DatabaseDataManager;
import xyz.yooniks.natalciaguilds.api.guild.Guild;
import xyz.yooniks.natalciaguilds.api.guild.member.GuildMember;
import xyz.yooniks.natalciaguilds.bukkit.guild.area.GuildAreaBukkit;
import xyz.yooniks.natalciaguilds.bukkit.helper.GuildHelper;

public class GuildManagerImpl implements GuildManagerBukkit {

  private final Map<String, Guild> guilds = new HashMap<>();
  private DatabaseDataManager<Guild> databaseManager;

  @Override
  public void addGuilds(List<Guild> guilds) {
    guilds.forEach(guild -> this.guilds.put(guild.getTag(), guild));
  }

  @Override
  public void createGuild(Guild guild) {
    this.guilds.put(guild.getTag(), guild);
    this.databaseManager.update(guild);
  }

  @Override
  public void removeGuild(Guild guild) {
    this.guilds.remove(guild.getTag());
    this.databaseManager.remove(guild);
  }

  @Override
  public DatabaseDataManager<Guild> getDatabaseManager() {
    return this.databaseManager;
  }

  @Override
  public void setDatabaseManager(
      DatabaseDataManager<Guild> databaseManager) {
    this.databaseManager = databaseManager;
  }

  @Override
  public List<Guild> getGuilds() {
    return ImmutableList.copyOf(guilds.values());
  }

  @Nullable
  @Override
  public Guild findByTag(String tag) {
    return this.guilds.get(tag);
  }

  @Nullable
  @Override
  public Guild findByName(String name) {
    return this.guilds.values()
        .stream()
        .filter(guild -> guild.getName().equalsIgnoreCase(name))
        .findFirst()
        .orElse(null);
  }

  @Nullable
  @Override
  public Guild findByMember(GuildMember member) {
    return this.guilds.values()
        .stream()
        .filter(guild -> this.identifiersOfGuildMembers(guild).contains(member.getIdentifier()))
        .findFirst()
        .orElse(null);
  }

  @Nullable
  @Override
  public Guild findByLocation(Location location) {
    return this.guilds.values()
        .stream()
        .filter(guild -> this.isInArea((GuildAreaBukkit) guild.getArea(), location))
        .findFirst()
        .orElse(null);
  }

  private List<UUID> identifiersOfGuildMembers(Guild guild) {
    return guild.getMembers().stream().map(GuildMember::getIdentifier).collect(Collectors.toList());
  }

  private boolean isInArea(GuildAreaBukkit area, Location location) {
    return GuildHelper.isInArea(area, area.getCenter(), location);
  }

}
