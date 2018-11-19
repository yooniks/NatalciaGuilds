package xyz.yooniks.natalciaguilds.api.guild.member;

import java.util.UUID;
import org.apache.commons.lang3.Validate;
import xyz.yooniks.natalciaguilds.api.Buildable;
import xyz.yooniks.natalciaguilds.api.ranking.Ranking;

public class GuildMemberBuilder implements Buildable<GuildMember> {

  private UUID identifier;
  private Ranking ranking;

  public GuildMemberBuilder withRanking(Ranking ranking) {
    this.ranking = ranking;
    return this;
  }

  public GuildMemberBuilder withIdentifier(UUID identifier) {
    this.identifier = identifier;
    return this;
  }

  @Override
  public GuildMember build() {
    Validate.notNull(this.identifier, "Identifier cannot be null!");
    Validate.notNull(this.ranking, "Ranking cannot be null!");

    return new GuildMemberImpl(this.identifier, this.ranking);
  }

}
