package xyz.yooniks.natalciaguilds.api.ranking.impl;

import xyz.yooniks.natalciaguilds.api.ranking.Ranking;

public class UserRanking implements Ranking {

  private int points, kills, deaths;

  public UserRanking(int points, int kills, int deaths) {
    this.points = points;
    this.kills = kills;
    this.deaths = deaths;
  }

  @Override
  public double calculateKDRatio() {
    return this.deaths == 0 ? this.getKills() : 1.0F * this.getKills() / this.getDeaths();
  }

  @Override
  public int getPoints() {
    return points;
  }

  public void setPoints(int points) {
    this.points = points;
  }

  @Override
  public int getKills() {
    return kills;
  }

  public void setKills(int kills) {
    this.kills = kills;
  }

  @Override
  public int getDeaths() {
    return deaths;
  }

  public void setDeaths(int deaths) {
    this.deaths = deaths;
  }

  @Override
  public String toString() {
    return "UserRanking{" +
        "points=" + points +
        ", kills=" + kills +
        ", deaths=" + deaths +
        '}';
  }

}
