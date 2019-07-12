package xyz.yooniks.natalciaguilds.bukkit.config.system;

import java.io.File;

public class Config {

  private final File file;
  private final Class<?> clazz;

  public Config(File file, Class<?> clazz) {
    this.file = file;
    this.clazz = clazz;
  }

  public File getFile() {
    return file;
  }

  public Class<?> getClazz() {
    return clazz;
  }

}
