package xyz.yooniks.natalciaguilds.bukkit.database.converter;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import xyz.yooniks.natalciaguilds.api.database.DatabaseDataConverter;

public class LocationConverter implements DatabaseDataConverter<String, Location> {

  @Override
  public Location fromDatabaseColumn(String string) {
    final String[] split = string.split(";");
    return new Location(
        Bukkit.getWorlds().get(0),
        Double.parseDouble(split[0]),
        Double.parseDouble(split[1]),
        Double.parseDouble(split[2]),
        Float.parseFloat(split[3]),
        Float.parseFloat(split[4])
    );
  }

  @Override
  public String toDatabaseColumn(Location location) {
    return String.format("% ,.2f;% ,.2f;% ,.2f;% ,.2f;% ,.2f", location.getX(), location.getY(),
        location.getZ(), location.getYaw(), location.getPitch());
  }

}
