package xyz.yooniks.natalciaguilds.api.ranking;

public interface Ranking {

  int getPoints();

  int getKills();

  int getDeaths();

  double calculateKDRatio();

}
