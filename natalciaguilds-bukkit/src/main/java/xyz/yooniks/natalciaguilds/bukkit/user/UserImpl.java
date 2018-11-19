package xyz.yooniks.natalciaguilds.bukkit.user;

import java.util.UUID;
import xyz.yooniks.natalciaguilds.api.guild.Guild;
import xyz.yooniks.natalciaguilds.api.ranking.Ranking;
import xyz.yooniks.natalciaguilds.api.user.User;

public class UserImpl implements User {

  private final UUID identifier;
  private final Ranking ranking;

  private Guild guild;

  public UserImpl(UUID identifier, Ranking ranking) {
    this.identifier = identifier;
    this.ranking = ranking;
  }

  @Override
  public UUID getIdentifier() {
    return identifier;
  }

  @Override
  public Ranking getRanking() {
    return ranking;
  }

  @Override
  public Guild getGuild() {
    return guild;
  }

  @Override
  public void setGuild(Guild guild) {
    this.guild = guild;
  }

}
