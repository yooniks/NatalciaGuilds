package xyz.yooniks.natalciaguilds.api.database;

import java.util.List;
import xyz.yooniks.natalciaguilds.api.guild.Guild;

public interface GuildDatabaseManager {

  List<Guild> findAll();

  void update(Guild guild);

  void remove(Guild guild);

}
