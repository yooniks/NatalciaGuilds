package xyz.yooniks.natalciaguilds.bukkit.guild;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import org.bukkit.Location;
import xyz.yooniks.natalciaguilds.api.guild.Guild;
import xyz.yooniks.natalciaguilds.api.guild.GuildManager;
import xyz.yooniks.natalciaguilds.api.member.GuildMember;
import xyz.yooniks.natalciaguilds.bukkit.GuildHelper;

public class GuildManagerImpl implements GuildManager {

  private final Map<String, Guild> guilds = new HashMap<>();

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
        .filter(guild -> this.guildsMembersByIdentifiers(guild).contains(member.getIdentifier()))
        .findFirst()
        .orElse(null);
  }

  @Nullable
  public Guild findByLocation(Location location) {
    return this.guilds.values()
        .stream()
        .filter(guild -> this.isInArea(guild, location))
        .findFirst()
        .orElse(null);
  }

  private List<UUID> guildsMembersByIdentifiers(Guild guild) {
    return guild.getMembers().stream().map(GuildMember::getIdentifier).collect(Collectors.toList());
  }

  private boolean isInArea(Guild guild, Location location) {
    return GuildHelper.isInArea(guild.getArea(), guild.getArea().asImpl((GuildAreaImpl)guild.getArea()).getCenter(), location);
  }

}
