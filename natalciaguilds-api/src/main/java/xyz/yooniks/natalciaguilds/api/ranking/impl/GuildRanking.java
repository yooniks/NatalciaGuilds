package xyz.yooniks.natalciaguilds.api.ranking.impl;

import xyz.yooniks.natalciaguilds.api.guild.Guild;
import xyz.yooniks.natalciaguilds.api.guild.member.GuildMember;
import xyz.yooniks.natalciaguilds.api.ranking.Ranking;

public class GuildRanking implements Ranking {

  private final Guild guild;

  public GuildRanking(Guild guild) {
    this.guild = guild;
  }

  @Override
  public double calculateKDRatio() {
    return this.getDeaths() == 0 ? this.getKills() : 1.0F * this.getKills() / this.getDeaths();
  }

  @Override
  public int getDeaths() {
    return this.guild.getMembers()
        .stream()
        .map(GuildMember::getRanking)
        .mapToInt(Ranking::getDeaths)
        .sum();
  }

  @Override
  public int getKills() {
    return this.guild.getMembers()
        .stream()
        .map(GuildMember::getRanking)
        .mapToInt(Ranking::getKills)
        .sum();
  }

  @Override
  public int getPoints() {
    return this.guild.getMembers()
        .stream()
        .map(GuildMember::getRanking)
        .mapToInt(Ranking::getPoints)
        .sum() / this.guild.getMembers().size();
  }

  @Override
  public String toString() {
    return "GuildRanking{" +
        "guild=" + guild +
        '}';
  }

}
