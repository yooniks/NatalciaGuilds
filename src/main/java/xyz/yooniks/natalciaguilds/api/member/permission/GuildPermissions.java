package xyz.yooniks.natalciaguilds.api.member.permission;

public final class GuildPermissions {

  public static final GuildPermission ADD_MEMBERS = new GuildPermissionBuilder()
      .withName("Dodawanie czlonkow gildii")
      .build();

  public static final GuildPermission REMOVE_MEMBERS = new GuildPermissionBuilder()
      .withName("Usuwanie czlonkow gildii")
      .build();

  private GuildPermissions() {
  }

}
