package xyz.yooniks.natalciaguilds.api.guild.member.permission;

public class GuildPermissionImpl implements GuildPermission {

  private final int id;
  private final String name;

  public GuildPermissionImpl(int id, String name) {
    this.id = id;
    this.name = name;
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
