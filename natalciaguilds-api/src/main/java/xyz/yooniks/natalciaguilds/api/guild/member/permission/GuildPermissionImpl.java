package xyz.yooniks.natalciaguilds.api.guild.member.permission;

public class GuildPermissionImpl implements GuildPermission {

  private final int id;
  private final String name;
  private final boolean removable;

  public GuildPermissionImpl(int id, String name, boolean removable) {
    this.id = id;
    this.name = name;
    this.removable = removable;
  }

  @Override
  public boolean isRemovable() {
    return removable;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public String getName() {
    return name;
  }
}
