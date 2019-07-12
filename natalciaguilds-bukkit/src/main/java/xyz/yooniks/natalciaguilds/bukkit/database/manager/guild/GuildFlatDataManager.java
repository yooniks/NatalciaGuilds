package xyz.yooniks.natalciaguilds.bukkit.database.manager.guild;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import xyz.yooniks.natalciaguilds.api.database.DatabaseDataManager;
import xyz.yooniks.natalciaguilds.api.guild.Guild;
import xyz.yooniks.natalciaguilds.bukkit.database.converter.DatabaseDataConverters;
import xyz.yooniks.natalciaguilds.bukkit.database.updater.DataUpdater;
import xyz.yooniks.natalciaguilds.bukkit.guild.area.GuildAreaBukkit;
import xyz.yooniks.natalciaguilds.bukkit.guild.area.GuildAreaImpl;
import xyz.yooniks.natalciaguilds.guild.GuildBuilder;

public class GuildFlatDataManager implements DatabaseDataManager<Guild> {

  private final File mainDir;
  private final File guildsDir;

  private final DataUpdater dataUpdater;

  public GuildFlatDataManager(Plugin plugin, DataUpdater dataUpdater) {
    this.dataUpdater = dataUpdater;

    this.mainDir = new File(plugin.getDataFolder(), "/data");
    this.guildsDir = new File(this.mainDir, "/guilds");
  }

  @Override
  public Guild load(Guild guild) {
    return null;
  }

  @Override
  public List<Guild> findAll() {
    this.check();

    return Arrays.stream(Objects.requireNonNull(this.guildsDir.listFiles()))
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
    final File file = new File(this.guildsDir, guild.getTag() + ".yml");
    if (!file.exists()) {
      return;
    }
    this.dataUpdater.execute(file::delete);
  }

  @Override
  public void update(Guild guild) {
    this.dataUpdater.execute(() -> {
      final File file = new File(this.guildsDir, guild.getTag() + ".yml");
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
    if (this.mainDir.exists()) {
      this.mainDir.mkdirs();
    }
    if (!this.guildsDir.exists()) {
      this.guildsDir.mkdirs();
    }
  }

}
