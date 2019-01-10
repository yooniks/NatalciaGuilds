package xyz.yooniks.natalciaguilds.bukkit.database.manager.guild;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import xyz.yooniks.natalciaguilds.api.database.DatabaseDataManager;
import xyz.yooniks.natalciaguilds.api.guild.Guild;
import xyz.yooniks.natalciaguilds.api.guild.area.GuildArea;
import xyz.yooniks.natalciaguilds.api.guild.member.GuildMember;
import xyz.yooniks.natalciaguilds.bukkit.database.converter.DatabaseDataConverters;
import xyz.yooniks.natalciaguilds.bukkit.database.updater.DataUpdater;
import xyz.yooniks.natalciaguilds.bukkit.guild.GuildBuilder;
import xyz.yooniks.natalciaguilds.bukkit.guild.area.GuildAreaBukkit;
import xyz.yooniks.natalciaguilds.bukkit.guild.area.GuildAreaImpl;

public class GuildSqlDatabaseManager implements DatabaseDataManager<Guild> {

  private final Connection connection;
  private final DataUpdater dataUpdater;

  public GuildSqlDatabaseManager(Connection connection, DataUpdater dataUpdater) {
    this.connection = connection;
    this.dataUpdater = dataUpdater;
  }

  @Override
  public Guild load(Guild guild) {
    return null;
  }

  @Override
  public List<Guild> findAll() {
    try (final PreparedStatement statement = this.connection
        .prepareStatement("SELECT * FROM guilds")) {
      final ResultSet result = statement.executeQuery();
      final List<Guild> guilds = new ArrayList<>();
      while (result.next()) {
        final String name = result.getString("guild_name");
        final String tag = result.getString("guild_tag");
        final GuildArea area = new GuildAreaImpl(
            DatabaseDataConverters.LOCATION_CONVERTER
                .fromDatabaseColumn(result.getString("area_location")),
            result.getInt("area_size"));
        final Set<GuildMember> members = DatabaseDataConverters.MEMBERS_CONVERTER
            .fromDatabaseColumn(result.getString("guild_members"));

        guilds.add(new GuildBuilder()
            .withName(name)
            .withTag(tag)
            .withArea(area)
            .withMembers(members)
            .build()
        );
      }
      return guilds;
    } catch (Exception ex) {
      ex.printStackTrace();
      return Collections.emptyList();
    }
  }

  @Override
  public void remove(Guild guild) {
    this.dataUpdater.execute(() -> {
      try (final PreparedStatement statement = this.connection
          .prepareStatement("DELETE FROM guilds WHERE guild_tag=?")) {
        statement.setString(1, guild.getTag());
        statement.execute();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    });
  }

  @Override
  public void update(Guild guild) {
    this.dataUpdater.execute(() -> {
      try (final PreparedStatement statement = this.connection
          .prepareStatement("INSERT INTO guilds "
              + "VALUES(?,?,?,?,?) ON DUPLICATE KEY UPDATE guild_tag=?")) {
      /*
      1. tag
      2. name
      3. area center location in string
      4. area size
      5. members with owner
       */
        statement.setString(1, guild.getTag());
        statement.setString(2, guild.getName());
        final GuildAreaBukkit area = (GuildAreaBukkit) guild.getArea();
        statement
            .setString(3,
                DatabaseDataConverters.LOCATION_CONVERTER.toDatabaseColumn(area.getCenter()));
        statement.setInt(4, area.getSize());
        statement.setString(5,
            DatabaseDataConverters.MEMBERS_CONVERTER.toDatabaseColumn(guild.getMembers()));

        statement.setString(6, guild.getTag());
        statement.execute();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    });
  }

}
