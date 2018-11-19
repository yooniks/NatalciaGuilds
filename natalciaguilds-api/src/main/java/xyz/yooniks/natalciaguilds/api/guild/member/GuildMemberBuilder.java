package xyz.yooniks.natalciaguilds.api.guild.member;

import java.util.UUID;
import xyz.yooniks.natalciaguilds.api.Buildable;

public class GuildMemberBuilder implements Buildable<GuildMember> {

  private UUID identifier;

  public GuildMemberBuilder withIdentifier(UUID identifier) {
    this.identifier = identifier;
    return this;
  }

  @Override
  public GuildMember build() {
    return new GuildMemberImpl(this.identifier);
  }

}
