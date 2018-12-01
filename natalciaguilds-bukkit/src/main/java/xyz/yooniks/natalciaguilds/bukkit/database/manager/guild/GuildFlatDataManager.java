package xyz.yooniks.natalciaguilds.bukkit.database.manager.guild;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.yooniks.natalciaguilds.api.database.DatabaseDataManager;
import xyz.yooniks.natalciaguilds.api.guild.Guild;
import xyz.yooniks.natalciaguilds.api.guild.area.GuildArea;
import xyz.yooniks.natalciaguilds.bukkit.NatalciaGuildsPlugin;
import xyz.yooniks.natalciaguilds.bukkit.config.SettingsConfig;
import xyz.yooniks.natalciaguilds.bukkit.database.converter.DatabaseDataConverters;
import xyz.yooniks.natalciaguilds.bukkit.guild.GuildAreaImpl;
import xyz.yooniks.natalciaguilds.bukkit.guild.GuildBuilder;
import xyz.yooniks.natalciaguilds.bukkit.helper.LocationHelper;

public class GuildFlatDataManager implements DatabaseDataManager<Guild> {

  private final File mainDir = new File(NatalciaGuildsPlugin.getInstance().getDataFolder(), "/data");
  private final File guildsDir = new File(this.mainDir, "/guilds");

  private final ExecutorService executorService = Executors.newFixedThreadPool(
      SettingsConfig.CONCURRENCY$THREADS);

  @Override
  public List<Guild> findAll() {
    this.check();

    return Arrays.stream(Objects.requireNonNull(this.mainDir.listFiles()))
        .map(YamlConfiguration::loadConfiguration)
        .map(yaml -> new GuildBuilder()
            .withName(yaml.getString("name"))
            .withTag(yaml.getString("tag"))
            .withArea(new GuildAreaImpl(
                LocationHelper.fromString(yaml.getString("area_location")),
                yaml.getInt("area_size")))
            .withMembers(DatabaseDataConverters.MEMBERS_CONVERTER.fromDatabaseColumn(yaml.getString("members")))
            .build())
        .collect(Collectors.toList());
  }

  @Override
  public void remove(Guild guild) {
    this.executorService.submit(() -> {
      final File file = new File(this.guildsDir, guild.getTag() + ".yml");
      if (!file.exists()) {
        return;
      }
      file.delete();
    });
  }

  @Override
  public void update(Guild guild) {
    this.executorService.submit(() -> {
      final File file = new File(this.guildsDir, guild.getTag() + ".yml");
      try {
        if (!file.exists()) {
          file.createNewFile();
        }
        final GuildArea area = guild.getArea();
        final YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        yaml.set("tag", guild.getTag());
        yaml.set("name", guild.getName());
        yaml.set("area_size", area.getSize());
        yaml.set("area_location",
            LocationHelper.toString(area.asImpl((GuildAreaImpl) area).getCenter()));
        yaml.set("members",
            DatabaseDataConverters.MEMBERS_CONVERTER.toDatabaseColumn(guild.getMembers()));
        yaml.save(file);
      } catch (IOException ex) {
        //cannot save guild data
        ex.printStackTrace();
      }
    });
  }

  private void check()  {
    if (this.mainDir.exists()) {
      this.mainDir.mkdir();
    }
    if (!this.guildsDir.exists()) {
      this.guildsDir.mkdir();
    }
  }

}
