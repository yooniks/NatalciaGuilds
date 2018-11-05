package xyz.yooniks.natalciaguilds.bukkit.guild;

import org.bukkit.Location;
import xyz.yooniks.natalciaguilds.api.GuildArea;

public class GuildAreaImpl implements GuildArea<Location> {

  private final Location firstCorner, secondCorner;

  public GuildAreaImpl(Location firstCorner, Location secondCorner) {
    this.firstCorner = firstCorner;
    this.secondCorner = secondCorner;
  }

  @Override
  public Location getCorner(boolean first) {
    return first ? this.firstCorner : secondCorner;
  }

}
