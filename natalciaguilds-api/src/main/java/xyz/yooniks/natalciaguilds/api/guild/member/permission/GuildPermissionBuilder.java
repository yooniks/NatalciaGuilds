package xyz.yooniks.natalciaguilds.api.guild.member.permission;

import org.apache.commons.lang3.Validate;
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
    Validate.isTrue(
        GuildPermissions.byId(this.id) != null,
        String.format("GuildPermission with id %d already exists!", this.id)
    );

    Validate.notNull(this.name, "Name cannot be null!");
    return new GuildPermissionImpl(this.id, this.name);
  }

}
