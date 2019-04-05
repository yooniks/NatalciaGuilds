package xyz.yooniks.natalciaguilds.bukkit.config.system;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import org.bukkit.configuration.file.YamlConfiguration;

public final class ConfigHelper {

  private ConfigHelper() {
  }

  public static synchronized void reload(Config config) {
    File configFile = checkFile(config.getFile());
    try {
      YamlConfiguration yaml = YamlConfiguration.loadConfiguration(configFile);
      parse(config.getClazz(), yaml);
      yaml.save(configFile);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public static synchronized void save(Config config) {
    File configFile = checkFile(config.getFile());
    try {
      YamlConfiguration yaml = YamlConfiguration.loadConfiguration(configFile);
      parseSave(config.getClazz(), yaml);
      yaml.save(configFile);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }

  public static void parseSave(Class<?> clazz, YamlConfiguration config) {
    try {
      for (Field f : clazz.getFields()) {
        if (Modifier.isStatic(f.getModifiers()) && Modifier.isPublic(f.getModifiers())) {
          String path = f.getName().toLowerCase().replace('$', '.');
          Object value = f.get(null);
          config.set(path, value);
        }
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  public static void parse(Class<?> clazz, YamlConfiguration config) {
    try {
      for (Field f : clazz.getFields()) {
        if (Modifier.isStatic(f.getModifiers()) && Modifier.isPublic(f.getModifiers())) {
          String path = f.getName().toLowerCase().replace('$', '.');
          Object def = f.get(null);
          Object value = config.get(path, def);
          config.set(path, value);
          f.set(null, value);
        }

      }
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  public static File checkFile(File file) {
    if (!file.exists()) {
      file.getParentFile().mkdirs();

      try {
        file.createNewFile();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }

    return file;
  }

}
