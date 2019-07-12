package xyz.yooniks.natalciaguilds.bukkit.config.system.color;

import java.util.List;
import xyz.yooniks.natalciaguilds.bukkit.helper.MessageHelper;

public class ListColorChanger implements ColorChanger<List<String>> {

  @Override
  public List<String> color(List<String> strings) {
    return MessageHelper.colored(strings);
  }

}
