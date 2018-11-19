package xyz.yooniks.natalciaguilds.api.guild;

import java.util.Set;
import xyz.yooniks.natalciaguilds.api.guild.area.GuildArea;
import xyz.yooniks.natalciaguilds.api.guild.member.GuildMember;
import xyz.yooniks.natalciaguilds.api.guild.member.permission.GuildPermission;
import xyz.yooniks.natalciaguilds.api.ranking.Ranking;

public interface Guild {

  String getTag();

  String getName();

  GuildArea getArea();

  Set<GuildMember> getMembers();

  GuildMember getOwner();

  Ranking getRanking();

  boolean hasPermission(GuildMember member, GuildPermission permission);

  boolean removePermission(GuildMember member, GuildPermission permission);

  boolean addPermission(GuildMember member, GuildPermission permission);

}
