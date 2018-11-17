package xyz.yooniks.natalciaguilds.api.guild.member.permission;

import org.apache.commons.lang.Validate;
import xyz.yooniks.natalciaguilds.api.Buildable;

public class GuildPermissionBuilder implements Buildable<GuildPermission> {

  private String name;
  private int id;

  public GuildPermissionBuilder withId(int id) {
    this.id = id;
    return this;
  }

  public GuildPermissionBuilder withName(String name) {
    this.name = name;
    return this;
  }

  @Override
  public GuildPermission build() {
    Validate.notNull(this.id, "Id cannot be null!");
    Validate.notNull(this.name, "Name cannot be null!");
    return new GuildPermissionImpl(this.id, this.name);
  }

}
