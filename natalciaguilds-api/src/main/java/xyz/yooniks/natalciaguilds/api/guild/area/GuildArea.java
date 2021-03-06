package xyz.yooniks.natalciaguilds.api.guild.area;

import xyz.yooniks.natalciaguilds.api.guild.member.GuildMember;

public interface GuildArea {

  boolean isInArea(GuildMember member);

  int getSize();

  void setSize(int size);

  void update();

}
