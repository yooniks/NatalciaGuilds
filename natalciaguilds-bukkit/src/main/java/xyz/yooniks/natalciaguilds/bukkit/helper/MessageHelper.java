package xyz.yooniks.natalciaguilds.bukkit.helper;

import java.util.List;
import java.util.stream.Collectors;
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

  public static void sendMessage(CommandSender sender, List<String> texts) {
    /*final String text = String.join("\n", texts);
    sender.sendMessage(text);*/
    texts.forEach(text -> MessageHelper.sendMessage(sender, text));
  }

  private MessageHelper() {
  }

}
