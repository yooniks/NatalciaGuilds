package xyz.yooniks.natalciaguilds.api.guild.member;

import java.util.UUID;
import javax.annotation.Nullable;
import xyz.yooniks.natalciaguilds.api.guild.Guild;
import xyz.yooniks.natalciaguilds.api.ranking.Ranking;

public class GuildMemberImpl implements GuildMember {

  private final UUID identifier;
  private final Ranking ranking;

  private Guild guild;

  public GuildMemberImpl(UUID identifier, Ranking ranking) {
    this.identifier = identifier;
    this.ranking = ranking;
  }

  @Override
  public UUID getIdentifier() {
    return identifier;
  }

  @Nullable
  @Override
  public Guild getGuild() {
    return guild;
  }

  @Override
  public Ranking getRanking() {
    return ranking;
  }

  public void setGuild(Guild guild) {
    this.guild = guild;
  }

}
