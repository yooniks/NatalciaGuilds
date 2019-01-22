package xyz.yooniks.natalciaguilds.bukkit.config;

import java.io.File;
import xyz.yooniks.natalciaguilds.bukkit.config.system.Config;

public class SettingsConfig extends Config {

  public static int CONCURRENCY$THREADS = 1;
  public static String DATABASE$TYPE = "mysql";
  public static int GUILD$CREATE$TAG_MIN = 2;
  public static int GUILD$CREATE$TAG_MAX = 4;
  public static int GUILD$CREATE$NAME_MIN = 6;
  public static int GUILD$CREATE$NAME_MAX = 22;
  public static int GUILD$CREATE$START_SIZE = 20;

  public SettingsConfig(File file, Class<?> clazz) {
    super(file, clazz);
  }


}
