package xyz.yooniks.natalciaguilds.bukkit.helper;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public final class LocationHelper {

  public static Location fromString(String location) {
    final String[] split = location.split(";");
    return new Location(
        Bukkit.getWorlds().get(0),
        Integer.valueOf(split[0]),
        Integer.valueOf(split[1]),
        Integer.valueOf(split[2])
    );
  }

  public static String toString(Location location) {
    return location.getBlockX() + ";" + location.getBlockY() + ";" + location.getBlockZ();
  }

  private LocationHelper() {
  }

}
