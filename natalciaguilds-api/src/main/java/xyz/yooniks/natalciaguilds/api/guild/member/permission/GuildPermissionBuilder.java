package xyz.yooniks.natalciaguilds.api.guild.member.permission;

import org.apache.commons.lang3.Validate;

public class GuildPermissionBuilder {

  private String name;
  private int id;
  private boolean removable = true;

  public GuildPermissionBuilder removable(boolean removable) {
    this.removable = removable;
    return this;
  }

  public GuildPermissionBuilder withId(int id) {
    this.id = id;
    return this;
  }

  public GuildPermissionBuilder withName(String name) {
    this.name = name;
    return this;
  }

  public GuildPermission build() {
    Validate.isTrue(
        GuildPermissions.byId(this.id) != null,
        String.format("GuildPermission with id %d already exists!", this.id)
    );

    Validate.notNull(this.name, "Name cannot be null!");
    return new GuildPermissionImpl(this.id, this.name, this.removable);
  }

}
