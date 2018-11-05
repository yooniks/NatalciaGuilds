package xyz.yooniks.natalciaguilds.api.member;

import java.util.Set;
import java.util.UUID;
import xyz.yooniks.natalciaguilds.api.Buildable;
import xyz.yooniks.natalciaguilds.api.member.permission.GuildPermission;

public class GuildMemberBuilder implements Buildable<GuildMember> {

  private UUID identifier;
  private Set<GuildPermission> permissions;

  public GuildMemberBuilder withIdentifier(UUID identifier) {
    this.identifier = identifier;
    return this;
  }

  public GuildMemberBuilder withPermissions(Set<GuildPermission> permissions) {
    this.permissions = permissions;
    return this;
  }

  @Override
  public GuildMember build() {
    return new GuildMemberImpl(this.identifier, this.permissions);
  }

}
