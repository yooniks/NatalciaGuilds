package xyz.yooniks.natalciaguilds.bukkit.database.concurrency;

//TODO: remove to natalciaguilds-core if it's possible
public interface TaskExecutor {

  void execute(Runnable runnable);

}
