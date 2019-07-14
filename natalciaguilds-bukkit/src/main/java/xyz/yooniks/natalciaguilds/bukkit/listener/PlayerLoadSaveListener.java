package xyz.yooniks.natalciaguilds.bukkit.listener;

import java.util.concurrent.CompletableFuture;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.yooniks.natalciaguilds.api.database.DatabaseDataManager;
import xyz.yooniks.natalciaguilds.api.user.User;
import xyz.yooniks.natalciaguilds.api.user.UserManager;
import xyz.yooniks.natalciaguilds.bukkit.database.concurrency.TaskExecutor;

public class PlayerLoadSaveListener implements Listener {

  private final UserManager userManager;
  private final TaskExecutor taskExecutor;

  private final DatabaseDataManager<User> userDatabaseDataManager;

  public PlayerLoadSaveListener(UserManager userManager,
      TaskExecutor taskExecutor,
      DatabaseDataManager<User> userDatabaseDataManager) {
    this.userManager = userManager;
    this.taskExecutor = taskExecutor;
    this.userDatabaseDataManager = userDatabaseDataManager;
  }

  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    final Player player = event.getPlayer();
    final User user = this.userManager.createUser(player.getUniqueId());

    //this.taskExecutor.execute(() -> this.userDatabaseDataManager.load(user));
    CompletableFuture.supplyAsync(() -> this.userDatabaseDataManager.load(user))
        .thenRun(
            () -> player.sendMessage("Zaladowano twoje dane! " + user.getRanking().toString()));
  }

  @EventHandler
  public void onQuit(PlayerQuitEvent event) {
  }

}
