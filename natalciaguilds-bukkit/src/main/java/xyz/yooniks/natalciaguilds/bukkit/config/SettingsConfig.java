package xyz.yooniks.natalciaguilds.bukkit.config;

import java.io.File;
import xyz.yooniks.natalciaguilds.bukkit.config.system.Config;

public class SettingsConfig extends Config {

  public SettingsConfig(File file, Class<?> clazz) {
    super(file, clazz);
  }

  public static int CONCURRENCY$THREADS = 1;
  public static String DATABASE$TYPE = "mysql";

}
