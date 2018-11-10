package xyz.yooniks.natalciaguilds.api.guild;

import xyz.yooniks.natalciaguilds.api.member.GuildMember;

public interface GuildArea {

  boolean isInArea(GuildMember member);

  int getSize();

  void setSize(int size);

  void update();

  default <T extends GuildArea> T asImpl(T impl) {
    return impl;
  }

}
