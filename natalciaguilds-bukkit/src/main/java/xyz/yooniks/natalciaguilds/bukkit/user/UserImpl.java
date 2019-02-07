package xyz.yooniks.natalciaguilds.bukkit.user;

import java.util.Objects;
import java.util.UUID;
import xyz.yooniks.natalciaguilds.api.guild.Guild;
import xyz.yooniks.natalciaguilds.api.ranking.Ranking;
import xyz.yooniks.natalciaguilds.api.ranking.impl.UserRanking;
import xyz.yooniks.natalciaguilds.api.user.User;

public class UserImpl implements User {

  private final UUID identifier;
  private final Ranking ranking = new UserRanking(500, 0, 0);

  private Guild guild;

  public UserImpl(UUID identifier) {
    this.identifier = identifier;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserImpl user = (UserImpl) o;
    return Objects.equals(identifier, user.identifier) &&
        Objects.equals(ranking, user.ranking) &&
        Objects.equals(guild, user.guild);
  }

  @Override
  public int hashCode() {
    return Objects.hash(identifier, ranking, guild);
  }

}
