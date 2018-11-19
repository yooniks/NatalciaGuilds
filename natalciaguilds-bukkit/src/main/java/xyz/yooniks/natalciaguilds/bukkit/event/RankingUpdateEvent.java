package xyz.yooniks.natalciaguilds.bukkit.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import xyz.yooniks.natalciaguilds.api.ranking.Ranking;

public class RankingUpdateEvent extends Event {

  private static final HandlerList handlerList = new HandlerList();

  private final Ranking ranking;

  public RankingUpdateEvent(Ranking ranking) {
    this.ranking = ranking;
  }

  public Ranking getRanking() {
    return ranking;
  }

  @Override
  public HandlerList getHandlers() {
    return handlerList;
  }

  public static HandlerList getHandlerList() {
    return handlerList;
  }

}
