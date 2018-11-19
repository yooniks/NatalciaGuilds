package xyz.yooniks.natalciaguilds.bukkit.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import xyz.yooniks.natalciaguilds.api.database.GuildDatabaseManager;
import xyz.yooniks.natalciaguilds.api.guild.Guild;
import xyz.yooniks.natalciaguilds.api.guild.GuildArea;
import xyz.yooniks.natalciaguilds.bukkit.guild.GuildAreaImpl;
import xyz.yooniks.natalciaguilds.bukkit.guild.GuildImpl;
import xyz.yooniks.natalciaguilds.bukkit.helper.LocationHelper;

public class GuildSqlDatabaseManager implements GuildDatabaseManager {

  private final Connection connection;

  public GuildSqlDatabaseManager(Connection connection) {
    this.connection = connection;
  }

  @Override
  public List<Guild> findAll() {
    try (final PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM guilds")) {
      final ResultSet result = statement.executeQuery();
      final List<Guild> guilds = new ArrayList<>();
      while (result.next()) {
        guilds.add(new GuildImpl(
            result.getString("guild_tag"), result.getString("guild_name"),
            new GuildAreaImpl(
                LocationHelper.fromString(result.getString("area_location")),
                result.getInt("area_size")
            )
        ));
      }
      return guilds;
    } catch (Exception ex) {
      ex.printStackTrace();
      return Collections.emptyList();
    }
  }

  @Override
  public void remove(Guild guild) {
    try (final PreparedStatement statement = this.connection.prepareStatement("DELETE FROM guilds WHERE guild_tag=?")) {
      statement.setString(1, guild.getTag());
      statement.execute();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Override
  public void update(Guild guild) {
    try (final PreparedStatement statement = this.connection.prepareStatement("INSERT INTO guilds "
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
      final GuildArea area = guild.getArea();
      statement.setString(3, LocationHelper.toString(area.asImpl((GuildAreaImpl)area).getCenter()));
      statement.setInt(4, area.getSize());
      statement.setString(5, DatabaseDataConverters.MEMBERS_CONVERTER.toDatabaseColumn(guild.getMembers()));

      statement.setString(6, guild.getTag());
      statement.execute();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

}
