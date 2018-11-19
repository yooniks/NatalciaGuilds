package xyz.yooniks.natalciaguilds.api.guild.member;

import java.util.UUID;
import javax.annotation.Nullable;
import xyz.yooniks.natalciaguilds.api.guild.Guild;
import xyz.yooniks.natalciaguilds.api.ranking.Ranking;

public interface GuildMember {

  UUID getIdentifier();

  @Nullable
  Guild getGuild();

  void setGuild(Guild guild);

  Ranking getRanking();

}
