package xyz.yooniks.natalciaguilds.bukkit.database.manager.guild;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.yooniks.natalciaguilds.api.database.DatabaseDataManager;
import xyz.yooniks.natalciaguilds.api.guild.Guild;
import xyz.yooniks.natalciaguilds.bukkit.NatalciaGuildsPlugin;
import xyz.yooniks.natalciaguilds.bukkit.database.converter.DatabaseDataConverters;
import xyz.yooniks.natalciaguilds.bukkit.database.updater.DataUpdater;
import xyz.yooniks.natalciaguilds.bukkit.guild.GuildBuilder;
import xyz.yooniks.natalciaguilds.bukkit.guild.area.GuildAreaBukkit;
import xyz.yooniks.natalciaguilds.bukkit.guild.area.GuildAreaImpl;

public class GuildFlatDataManager implements DatabaseDataManager<Guild> {

  private final FileHolder fileHolder = new FileHolder();

  private final DataUpdater dataUpdater;

  public GuildFlatDataManager(
      DataUpdater dataUpdater) {
    this.dataUpdater = dataUpdater;
  }

  @Override
  public Guild load(Guild guild) {
    return null;
  }

  @Override
  public List<Guild> findAll() {
    this.check();

    return Arrays.stream(Objects.requireNonNull(this.fileHolder.guildsDir.listFiles()))
        .map(YamlConfiguration::loadConfiguration)
        .map(yaml -> new GuildBuilder()
            .withName(yaml.getString("name"))
            .withTag(yaml.getString("tag"))
            .withArea(new GuildAreaImpl(
                DatabaseDataConverters.LOCATION_CONVERTER
                    .fromDatabaseColumn(yaml.getString("area_location")),
                yaml.getInt("area_size")))
            .withMembers(DatabaseDataConverters.MEMBERS_CONVERTER.fromDatabaseColumn(yaml.getString("members")))
            .build())
        .collect(Collectors.toList());
  }

  @Override
  public void remove(Guild guild) {
    this.dataUpdater.execute(() -> {
      final File file = new File(this.fileHolder.guildsDir, guild.getTag() + ".yml");
      if (!file.exists()) {
        return;
      }
      file.delete();
    });
  }

  @Override
  public void update(Guild guild) {
    this.dataUpdater.execute(() -> {
      final File file = new File(this.fileHolder.guildsDir, guild.getTag() + ".yml");
      try {
        if (!file.exists()) {
          file.createNewFile();
        }
        final GuildAreaBukkit area = (GuildAreaBukkit) guild.getArea();
        final YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        yaml.set("tag", guild.getTag());
        yaml.set("name", guild.getName());
        yaml.set("area_size", area.getSize());
        yaml.set("area_location",
            DatabaseDataConverters.LOCATION_CONVERTER
                .toDatabaseColumn(area.getCenter()));
        yaml.set("members",
            DatabaseDataConverters.MEMBERS_CONVERTER.toDatabaseColumn(guild.getMembers()));
        yaml.save(file);
      } catch (IOException ex) {
        //cannot save guild data
        ex.printStackTrace();
      }
    });
  }

  private void check() {
    if (this.fileHolder.mainDir.exists()) {
      this.fileHolder.mainDir.mkdir();
    }
    if (!this.fileHolder.guildsDir.exists()) {
      this.fileHolder.guildsDir.mkdir();
    }
  }

  private class FileHolder {

    private final File mainDir = new File(NatalciaGuildsPlugin.getInstance()
        .getDataFolder(), "/data");
    private final File guildsDir = new File(this.mainDir, "/guilds");
  }

}
