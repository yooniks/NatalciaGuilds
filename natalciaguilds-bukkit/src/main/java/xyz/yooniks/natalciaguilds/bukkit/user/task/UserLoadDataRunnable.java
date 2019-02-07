package xyz.yooniks.natalciaguilds.bukkit.user.task;

import xyz.yooniks.natalciaguilds.api.database.DatabaseDataManager;
import xyz.yooniks.natalciaguilds.api.user.User;

public class UserLoadDataRunnable implements Runnable {

  private final User user;
  private final DatabaseDataManager<User> userDataManager;

  public UserLoadDataRunnable(User user,
      DatabaseDataManager<User> userDataManager) {
    this.user = user;
    this.userDataManager = userDataManager;
  }

  @Override
  public void run() {
    this.userDataManager.load(this.user);
  }

}
