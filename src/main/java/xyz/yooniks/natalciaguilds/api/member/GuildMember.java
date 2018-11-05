package xyz.yooniks.natalciaguilds.api.member;

import java.util.UUID;
import xyz.yooniks.natalciaguilds.api.member.permission.GuildPermission;

public interface GuildMember {

  UUID getIdentifier();

  boolean can(GuildPermission permission);

}
