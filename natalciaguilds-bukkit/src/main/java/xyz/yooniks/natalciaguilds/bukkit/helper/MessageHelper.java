package xyz.yooniks.natalciaguilds.bukkit.helper;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class MessageHelper {

  public static String colored(String text) {
    return ChatColor.translateAlternateColorCodes('&', text);
  }

  public static void sendMessage(CommandSender sender, String text) {
    sender.sendMessage(colored(text));
  }

  private MessageHelper() {
  }

}
