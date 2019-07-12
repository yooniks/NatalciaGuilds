package xyz.yooniks.natalciaguilds.bukkit.config.system.color;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ColorChangers {

  private final Set<ColorChanger> colorChangers = new HashSet<>(
      Arrays.asList(new StringColorChanger(), new ListColorChanger())
  );

  public boolean addColorChanger(ColorChanger colorChanger) {
    return this.colorChangers.add(colorChanger);
  }

  public Optional<ColorChanger> byClass(Class<? extends ColorChanger> clazz) {
    return this.colorChangers
        .stream()
        .filter(colorChanger -> colorChanger.getClass().getName().equalsIgnoreCase(clazz.getName()))
        .findFirst();
  }

}
