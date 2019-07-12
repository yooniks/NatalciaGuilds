package xyz.yooniks.natalciaguilds.guild;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import xyz.yooniks.natalciaguilds.api.guild.Guild;
import xyz.yooniks.natalciaguilds.api.guild.GuildInfo;
import xyz.yooniks.natalciaguilds.api.guild.area.GuildArea;
import xyz.yooniks.natalciaguilds.api.guild.member.GuildMember;
import xyz.yooniks.natalciaguilds.api.guild.member.permission.GuildPermission;
import xyz.yooniks.natalciaguilds.api.ranking.Ranking;
import xyz.yooniks.natalciaguilds.guild.member.permission.GuildPermissions;
import xyz.yooniks.natalciaguilds.ranking.GuildRanking;

public class GuildImpl implements Guild {

  private final String tag, name;
  private final GuildArea area;
  private final Ranking ranking;

  private final Set<GuildMember> members;
  private final Multimap<UUID, GuildPermission> permissions = ArrayListMultimap.create();
  private final GuildInfo info = new GuildInfoImpl();

  public GuildImpl(String tag, String name, GuildArea area, Set<GuildMember> members) {
    this.tag = tag;
    this.name = name;
    this.area = area;
    this.members = members;
    this.ranking = new GuildRanking(this);
  }

  public GuildImpl(String tag, String name, GuildArea area) {
    this.tag = tag;
    this.name = name;
    this.area = area;
    this.members = new HashSet<>();
    this.ranking = new GuildRanking(this);
  }

  @Override
  public void addPermission(GuildMember member, GuildPermission permission) {
    permissions.put(member.getIdentifier(), permission);
  }

  @Override
  public void removePermission(GuildMember member, GuildPermission permission) {
    permissions.remove(member.getIdentifier(), permission);
  }

  @Override
  public boolean hasPermission(GuildMember member, GuildPermission permission) {
    return getOwner().getIdentifier().equals(member.getIdentifier())
        || permissions.containsEntry(member.getIdentifier(), permission);
  }

  @Override
  public GuildInfo getInfo() {
    return info;
  }

  @Override
  public Set<GuildMember> getMembers() {
    return new HashSet<>(members);
  }

  @Override
  public void addMember(GuildMember member) {
    this.members.add(member);
  }

  @Override
  public void removeMember(GuildMember member) {
    this.members.remove(member);
  }

  @Override
  public boolean isMember(GuildMember member) {
    return this.members.contains(member);
  }

  @Override
  public GuildMember findMemberByIdentifier(UUID identifier) {
    return this.members
        .stream()
        .filter(member -> member.getIdentifier().equals(identifier))
        .findFirst()
        .orElse(null);
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
  public Ranking getRanking() {
    return ranking;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GuildImpl guild = (GuildImpl) o;
    return Objects.equals(tag, guild.tag) &&
        Objects.equals(name, guild.name) &&
        Objects.equals(area, guild.area) &&
        Objects.equals(ranking, guild.ranking) &&
        Objects.equals(members, guild.members) &&
        Objects.equals(permissions, guild.permissions) &&
        Objects.equals(info, guild.info);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tag, name, area, ranking, members, permissions, info);
  }
}
