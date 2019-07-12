package xyz.yooniks.natalciaguilds.guild.member.permission;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import xyz.yooniks.natalciaguilds.api.guild.member.permission.GuildPermission;

public final class GuildPermissions {

  private static final AtomicInteger ID_INCREMENTER = new AtomicInteger();

  public static final GuildPermission ADD_MEMBERS = new GuildPermissionBuilder()
      .withId(ID_INCREMENTER.getAndIncrement())
      .withName("Dodawanie czlonkow gildii")
      .build();

  public static final GuildPermission REMOVE_MEMBERS = new GuildPermissionBuilder()
      .withId(ID_INCREMENTER.getAndIncrement())
      .withName("Usuwanie czlonkow gildii")
      .build();

  public static final GuildPermission PLACE_BLOCKS = new GuildPermissionBuilder()
      .withId(ID_INCREMENTER.getAndIncrement())
      .withName("Kladzenie blokow na terenie gildii")
      .build();

  public static final GuildPermission BREAK_BLOCKS = new GuildPermissionBuilder()
      .withId(ID_INCREMENTER.getAndIncrement())
      .withName("Rozwalanie blokow na terenie gildii")
      .build();

  public static final GuildPermission OWNER = new GuildPermissionBuilder()
      .withId(ID_INCREMENTER.getAndIncrement())
      .withName("Zalozyciel gildii")
      .removable(false)
      .build();

  public static final Set<GuildPermission> PERMISSIONS = new HashSet<>(
      Arrays.asList(ADD_MEMBERS, REMOVE_MEMBERS, PLACE_BLOCKS, BREAK_BLOCKS, OWNER));

  @Nullable
  public static GuildPermission byId(int id) {
    return PERMISSIONS.stream()
        .filter(guildPermission -> guildPermission.getId() == id)
        .findFirst()
        .orElse(null);
  }

  private GuildPermissions() {
  }

}
