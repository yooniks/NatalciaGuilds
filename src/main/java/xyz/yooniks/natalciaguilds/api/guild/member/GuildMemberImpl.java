package xyz.yooniks.natalciaguilds.api.guild.member;

import java.util.UUID;
import javax.annotation.Nullable;
import xyz.yooniks.natalciaguilds.api.guild.Guild;

public class GuildMemberImpl implements GuildMember {

  private final UUID identifier;

  private Guild guild;

  public GuildMemberImpl(UUID identifier) {
    this.identifier = identifier;
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

  public void setGuild(Guild guild) {
    this.guild = guild;
  }

  @Override
  public int getPoints() {
    return 0;
  }

  @Override
  public int getKills() {
    return 0;
  }

  @Override
  public int getDeaths() {
    return 0;
  }

  @Override
  public double calculateKDRatio() {
    return this.getDeaths() == 0 ? this.getKills() : 1.0F * this.getKills() / this.getDeaths();
  }
}
