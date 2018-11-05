package xyz.yooniks.natalciaguilds.api;

import xyz.yooniks.natalciaguilds.api.member.GuildMember;

public interface GuildArea {

  boolean isInArea(GuildMember member);

  int getSize();

  void update();

}
