package xyz.yooniks.natalciaguilds.bukkit.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.yooniks.natalciaguilds.api.user.User;
import xyz.yooniks.natalciaguilds.api.user.UserManager;

public class PlayerLoadSaveListener implements Listener {

  private final UserManager userManager;

  public PlayerLoadSaveListener(UserManager userManager) {
    this.userManager = userManager;
  }

  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    final Player player = event.getPlayer();
    final User user = this.userManager.createUser(player.getUniqueId());
  }

  @EventHandler
  public void onQuit(PlayerQuitEvent event) {

  }

}
