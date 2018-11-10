package xyz.yooniks.natalciaguilds.api.guild;

import javax.annotation.Nullable;
import xyz.yooniks.natalciaguilds.api.member.GuildMember;

public interface GuildManager {

  @Nullable
  Guild findByTag(String tag);

  @Nullable
  Guild findByName(String name);

  @Nullable
  Guild findByMember(GuildMember member);

}
