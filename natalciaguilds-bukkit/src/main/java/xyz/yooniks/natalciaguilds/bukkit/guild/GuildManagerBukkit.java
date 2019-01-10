package xyz.yooniks.natalciaguilds.bukkit.guild;

import javax.annotation.Nullable;
import org.bukkit.Location;
import xyz.yooniks.natalciaguilds.api.guild.Guild;
import xyz.yooniks.natalciaguilds.api.guild.GuildManager;

public interface GuildManagerBukkit extends GuildManager {

  @Nullable
  Guild findByLocation(Location location);

}
