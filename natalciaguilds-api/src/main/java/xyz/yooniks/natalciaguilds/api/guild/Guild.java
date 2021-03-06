package xyz.yooniks.natalciaguilds.api.guild;

import java.util.Set;
import java.util.UUID;
import xyz.yooniks.natalciaguilds.api.guild.area.GuildArea;
import xyz.yooniks.natalciaguilds.api.guild.member.GuildMember;
import xyz.yooniks.natalciaguilds.api.guild.member.permission.GuildPermission;
import xyz.yooniks.natalciaguilds.api.ranking.Ranking;

public interface Guild {

  String getTag();

  String getName();

  GuildArea getArea();

  Set<GuildMember> getMembers();

  void addMember(GuildMember member);

  void removeMember(GuildMember member);

  boolean isMember(GuildMember member);

  GuildMember findMemberByIdentifier(UUID identifier);

  GuildMember getOwner();

  Ranking getRanking();

  GuildInfo getInfo();

  boolean hasPermission(GuildMember member, GuildPermission permission);

  void removePermission(GuildMember member, GuildPermission permission);

  void addPermission(GuildMember member, GuildPermission permission);

}
