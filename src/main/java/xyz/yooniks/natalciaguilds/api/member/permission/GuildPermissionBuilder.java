package xyz.yooniks.natalciaguilds.api.member.permission;

import xyz.yooniks.natalciaguilds.api.Buildable;

public class GuildPermissionBuilder implements Buildable<GuildPermission> {

  private String name;

  public GuildPermissionBuilder withName(String name) {
    this.name = name;
    return this;
  }

  @Override
  public GuildPermission build() {
    return () -> this.name;
  }

}
