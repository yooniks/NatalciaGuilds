package xyz.yooniks.natalciaguilds.bukkit.helper;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public final class MessageHelper {

  public static String colored(String text) {
    return ChatColor.translateAlternateColorCodes('&', text);
  }

  public static void sendMessage(Player player, String text) {
    player.sendMessage(colored(text));
  }

  private MessageHelper() {
  }

}
