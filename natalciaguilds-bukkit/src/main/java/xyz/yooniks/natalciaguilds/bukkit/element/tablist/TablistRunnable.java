package xyz.yooniks.natalciaguilds.bukkit.element.tablist;

import org.bukkit.Bukkit;

public class TablistRunnable implements Runnable {

  private final Tablist tablist;

  public TablistRunnable(Tablist tablist) {
    this.tablist = tablist;
  }

  @Override
  public void run() {
    Bukkit.getOnlinePlayers().forEach(this.tablist::update);
  }

}
