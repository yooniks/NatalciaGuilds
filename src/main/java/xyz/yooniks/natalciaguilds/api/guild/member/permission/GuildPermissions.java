package xyz.yooniks.natalciaguilds.api.guild.member.permission;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;

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

  public static final GuildPermission OWNER = new GuildPermissionBuilder()
      .withId(ID_INCREMENTER.getAndIncrement())
      .withName("Zalozyciel gildii")
      .build();

  public static final Set<GuildPermission> PERMISSIONS_AS_SET = new HashSet<>(
      Arrays.asList(ADD_MEMBERS, REMOVE_MEMBERS));

  @Nullable
  public static GuildPermission byId(int id) {
    return PERMISSIONS_AS_SET.stream()
        .filter(guildPermission -> guildPermission.getId() == id)
        .findFirst()
        .orElse(null);
  }

  private GuildPermissions() {
  }

}
