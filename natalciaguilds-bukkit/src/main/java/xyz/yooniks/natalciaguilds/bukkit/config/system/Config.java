package xyz.yooniks.natalciaguilds.bukkit.config.system;

import static xyz.yooniks.natalciaguilds.bukkit.config.system.ConfigHelper.checkFile;
import static xyz.yooniks.natalciaguilds.bukkit.config.system.ConfigHelper.parse;
import static xyz.yooniks.natalciaguilds.bukkit.config.system.ConfigHelper.parseSave;

import java.io.File;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {

  private final File file;
  private final Class<?> clazz;

  public Config(File file, Class<?> clazz) {
    this.file = file;
    this.clazz = clazz;
    this.reload();
  }

  public void reload() {
    File configFile = checkFile(this.file);
    try {
      YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
      parse(this.clazz, config);
      config.save(configFile);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void save() {
    File configFile = checkFile(this.file);
    try {
      YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
      parseSave(this.clazz, config);
      config.save(configFile);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }

}
