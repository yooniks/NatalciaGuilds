package xyz.yooniks.natalciaguilds.bukkit.config.system;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Optional;
import org.apache.commons.lang.StringUtils;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.yooniks.natalciaguilds.bukkit.config.system.color.ColorChanger;
import xyz.yooniks.natalciaguilds.bukkit.config.system.color.ColorChangers;
import xyz.yooniks.natalciaguilds.bukkit.config.system.color.ColorValue;

public class ConfigManager {

  private final ColorChangers colorChangers = new ColorChangers();

  public ColorChangers getColorChangers() {
    return colorChangers;
  }

  public void reload(Config config) {
    final File configFile = checkFile(config.getFile());
    try {
      final YamlConfiguration yaml = YamlConfiguration.loadConfiguration(configFile);
      parse(config.getClazz(), yaml);
      yaml.save(configFile);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void save(Config config) {
    final File configFile = checkFile(config.getFile());
    try {
      final YamlConfiguration yaml = YamlConfiguration.loadConfiguration(configFile);
      parseSave(config.getClazz(), yaml);
      yaml.save(configFile);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }

  private void parseSave(Class<?> clazz, YamlConfiguration config) {
    try {
      for (Field f : clazz.getFields()) {
        if (Modifier.isStatic(f.getModifiers()) && Modifier.isPublic(f.getModifiers())) {
          final String path = StringUtils.replace(f.getName().toLowerCase(), "$", ".");
          final Object value = f.get(null);
          config.set(path, value);
        }
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  private void parse(Class<?> clazz, YamlConfiguration config) {
    try {
      for (Field f : clazz.getFields()) {
        if (Modifier.isStatic(f.getModifiers()) && Modifier.isPublic(f.getModifiers())) {
          final String path = StringUtils.replace(f.getName().toLowerCase(), "$", ".");
          final Object def = f.get(null);
          Object value = config.get(path, def);

          config.set(path, value);

          final ColorValue color = f.getAnnotation(ColorValue.class);
          if (color != null) {
            final Optional<ColorChanger> optionalColorChanger = this.colorChangers
                .byClass(color.colorBy());
            if (optionalColorChanger.isPresent()) {
              value = optionalColorChanger.get().color(value);
            }
          }
          f.set(null, value);
        }
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  private File checkFile(File file) {
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }

    return file;
  }

}
