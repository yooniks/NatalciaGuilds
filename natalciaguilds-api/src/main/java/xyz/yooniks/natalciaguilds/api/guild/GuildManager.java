package xyz.yooniks.natalciaguilds.api.guild;

import java.util.List;
import javax.annotation.Nullable;
import xyz.yooniks.natalciaguilds.api.database.DatabaseDataManager;
import xyz.yooniks.natalciaguilds.api.guild.member.GuildMember;

public interface GuildManager {

  @Nullable
  Guild findByTag(String tag);

  @Nullable
  Guild findByName(String name);

  @Nullable
  Guild findByMember(GuildMember member);

  void createGuild(Guild guild);

  void removeGuild(Guild guild);

  List<Guild> getGuilds();

  DatabaseDataManager<Guild> getDatabaseManager();

  void setDatabaseManager(DatabaseDataManager<Guild> databaseManager);

  void addGuilds(List<Guild> guilds);

}
