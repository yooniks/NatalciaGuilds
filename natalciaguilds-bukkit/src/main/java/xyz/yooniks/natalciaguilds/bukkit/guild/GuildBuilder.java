package xyz.yooniks.natalciaguilds.bukkit.guild;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.Validate;
import xyz.yooniks.natalciaguilds.api.Buildable;
import xyz.yooniks.natalciaguilds.api.guild.Guild;
import xyz.yooniks.natalciaguilds.api.guild.area.GuildArea;
import xyz.yooniks.natalciaguilds.api.guild.member.GuildMember;
import xyz.yooniks.natalciaguilds.api.ranking.Ranking;

public class GuildBuilder implements Buildable<Guild> {

  private String name, tag;
  private GuildArea area;
  private Set<GuildMember> members;

  public GuildBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public GuildBuilder withTag(String tag) {
    this.tag = tag;
    return this;
  }

  public GuildBuilder withMembers(Set<GuildMember> members) {
    this.members = members;
    return this;
  }

  public GuildBuilder withArea(GuildArea area) {
    this.area = area;
    return this;
  }

  @Override
  public Guild build() {
    this.validate(this.name, this.tag, this.area, this.members);

    return new GuildImpl(this.name, this.tag, this.area, this.members);
  }

  private void validate(Object... toValidate) {
    Arrays.stream(toValidate).forEach(Validate::notNull);
  }

}
