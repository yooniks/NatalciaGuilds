package xyz.yooniks.natalciaguilds.bukkit.guild;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import xyz.yooniks.natalciaguilds.api.guild.Guild;
import xyz.yooniks.natalciaguilds.api.guild.area.GuildArea;
import xyz.yooniks.natalciaguilds.api.guild.member.GuildMember;
import xyz.yooniks.natalciaguilds.api.guild.member.permission.GuildPermission;
import xyz.yooniks.natalciaguilds.api.guild.member.permission.GuildPermissions;
import xyz.yooniks.natalciaguilds.api.ranking.Ranking;

public class GuildImpl implements Guild {

  private final String tag, name;
  private final GuildArea area;
  private final Ranking ranking;

  private final Set<GuildMember> members = new HashSet<>();
  private final Multimap<UUID, GuildPermission> permissions = ArrayListMultimap.create();

  public GuildImpl(String tag, String name, GuildArea area, Ranking ranking) {
    this.tag = tag;
    this.name = name;
    this.area = area;
    this.ranking = ranking;
  }

  @Override
  public boolean addPermission(GuildMember member, GuildPermission permission) {
    return permissions.put(member.getIdentifier(), permission);
  }

  @Override
  public boolean removePermission(GuildMember member, GuildPermission permission) {
    return permissions.remove(member.getIdentifier(), permission);
  }

  @Override
  public boolean hasPermission(GuildMember member, GuildPermission permission) {
    return permissions.containsEntry(member.getIdentifier(), permission);
  }

  @Override
  public GuildMember getOwner() {
    return this.members.stream()
        .filter(member -> this.hasPermission(member, GuildPermissions.OWNER))
        .findFirst()
        .orElse(null);
  }

  @Override
  public String getTag() {
    return tag;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public GuildArea getArea() {
    return area;
  }

  @Override
  public Set<GuildMember> getMembers() {
    return members;
  }

  @Override
  public Ranking getRanking() {
    return ranking;
  }

}
