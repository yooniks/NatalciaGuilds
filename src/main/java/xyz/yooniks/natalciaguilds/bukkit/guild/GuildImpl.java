package xyz.yooniks.natalciaguilds.bukkit.guild;

import java.util.HashSet;
import java.util.Set;
import xyz.yooniks.natalciaguilds.api.guild.Guild;
import xyz.yooniks.natalciaguilds.api.guild.GuildArea;
import xyz.yooniks.natalciaguilds.api.member.GuildMember;

public class GuildImpl implements Guild {

  private final String tag, name;
  private final GuildArea area;

  private final Set<GuildMember> members = new HashSet<>();

  public GuildImpl(String tag, String name, GuildArea area) {
    this.tag = tag;
    this.name = name;
    this.area = area;
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

}
