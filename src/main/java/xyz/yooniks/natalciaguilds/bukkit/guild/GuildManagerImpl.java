package xyz.yooniks.natalciaguilds.bukkit.guild;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import xyz.yooniks.natalciaguilds.api.Guild;
import xyz.yooniks.natalciaguilds.api.GuildManager;

public class GuildManagerImpl implements GuildManager {

  private final Map<String, Guild> guilds = new HashMap<>();

  @Nullable
  @Override
  public Guild findByTag(String tag) {
    return this.guilds.get(tag);
  }

}
