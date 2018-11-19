package xyz.yooniks.natalciaguilds.bukkit.helper;

import org.bukkit.Location;
import xyz.yooniks.natalciaguilds.api.guild.area.GuildArea;

public final class GuildHelper {

  //sam wygladasz jak gowno
  public static boolean isInArea(GuildArea area, Location areaCenter, Location location) {
    final int centermax_x = (int) (areaCenter.getX() + area.getSize());
    final int centermin_x = (int) (areaCenter.getX() - area.getSize());
    final int centermax_z = (int) (areaCenter.getZ() + area.getSize());
    final int centermin_z = (int) (areaCenter.getZ() - area.getSize());

    return location.getX() < centermax_x && location.getX() >= centermin_x
        && location.getZ() < centermax_z && location.getZ() >= centermin_z;
  }

  private GuildHelper() {
  }

  //private static int centerCoordinate(G)

}
