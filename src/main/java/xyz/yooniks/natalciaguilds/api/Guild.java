package xyz.yooniks.natalciaguilds.api;

import java.util.Set;
import xyz.yooniks.natalciaguilds.api.member.GuildMember;

public interface Guild {

  String getTag();

  String getName();

  GuildArea<?> getArea();

  Set<GuildMember> getMembers();

}
