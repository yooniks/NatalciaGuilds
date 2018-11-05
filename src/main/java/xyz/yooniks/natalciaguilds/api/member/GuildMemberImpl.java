package xyz.yooniks.natalciaguilds.api.member;

import java.util.Set;
import java.util.UUID;
import xyz.yooniks.natalciaguilds.api.member.permission.GuildPermission;

public class GuildMemberImpl implements GuildMember {

  private final UUID identifier;
  private final Set<GuildPermission> permissions;

  public GuildMemberImpl(UUID identifier, Set<GuildPermission> permissions) {
    this.identifier = identifier;
    this.permissions = permissions;
  }

  @Override
  public UUID getIdentifier() {
    return identifier;
  }

  @Override
  public boolean can(GuildPermission permission) {
    return this.permissions.contains(permission);
  }

}
