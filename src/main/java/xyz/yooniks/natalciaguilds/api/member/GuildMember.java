package xyz.yooniks.natalciaguilds.api.member;

import java.util.UUID;
import javax.annotation.Nullable;
import xyz.yooniks.natalciaguilds.api.guild.Guild;
import xyz.yooniks.natalciaguilds.api.member.permission.GuildPermission;

public interface GuildMember {

  UUID getIdentifier();

  @Nullable
  Guild getGuild();

  boolean can(GuildPermission permission);

}
