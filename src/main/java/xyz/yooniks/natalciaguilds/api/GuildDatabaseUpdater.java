package xyz.yooniks.natalciaguilds.api;

import java.util.List;
import xyz.yooniks.natalciaguilds.api.guild.Guild;

public interface GuildDatabaseUpdater {

  List<Guild> findAll();

  void update(Guild guild);

  void remove(Guild guild);

}
