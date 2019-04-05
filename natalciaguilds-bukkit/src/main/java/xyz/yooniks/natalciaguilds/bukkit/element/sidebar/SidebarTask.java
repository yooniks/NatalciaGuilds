package xyz.yooniks.natalciaguilds.bukkit.element.sidebar;

import java.util.List;
import me.tigerhix.lib.scoreboard.ScoreboardLib;
import me.tigerhix.lib.scoreboard.common.EntryBuilder;
import me.tigerhix.lib.scoreboard.type.Entry;
import me.tigerhix.lib.scoreboard.type.Scoreboard;
import me.tigerhix.lib.scoreboard.type.ScoreboardHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.yooniks.natalciaguilds.api.user.User;
import xyz.yooniks.natalciaguilds.api.user.UserManager;

public class SidebarTask implements Runnable {

  /*private final String title;
  private final List<Entry> elements;

  public SidebarTask(String title, List<Entry> elements) {
    this.title = title;
    this.elements = elements;
  }*/

  private final UserManager userManager;

  public SidebarTask(UserManager userManager) {
    this.userManager = userManager;
  }

  @Override
  public void run() {
    for (Player player : Bukkit.getOnlinePlayers()) {
      final User user = this.userManager.createUser(player.getUniqueId());

      final Scoreboard scoreboard = ScoreboardLib.createScoreboard(player)
          .setHandler(new ScoreboardHandler() {

            @Override
            public String getTitle(Player player) {
              return "";
            }

            @Override
            public List<Entry> getEntries(Player player) {
              return new EntryBuilder()
                  .next("&7Gildia: &6" + (user.getGuild() == null ? "brak"
                      : user.getGuild().getTag()))
                  .next("&7Smierci: &6" + user.getRanking().getDeaths())
                  .build();
            }

          })
          .setUpdateInterval(20L);
      if (!scoreboard.isActivated()) {
        scoreboard.activate();
      }
    }
  }

}
