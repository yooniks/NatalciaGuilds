package xyz.yooniks.natalciaguilds.bukkit.config.system.color;

import xyz.yooniks.natalciaguilds.bukkit.helper.MessageHelper;

public class StringColorChanger implements ColorChanger<String> {

  @Override
  public String color(String text) {
    return MessageHelper.colored(text);
  }

}
