package xyz.yooniks.natalciaguilds.bukkit.helper;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public final class MessageHelper {

  private MessageHelper() {
  }

  public static String colored(String text) {
    return ChatColor.translateAlternateColorCodes('&', text);
  }

  public static void sendMessage(CommandSender sender, String text) {
    sender.sendMessage(colored(text));
  }

  public static void sendMessage(CommandSender sender, MessageBuilder messageBuilder) {
    sender.sendMessage(colored(messageBuilder.text));
  }

  public static void sendMessage(CommandSender sender, List<String> texts) {
    /*final String text = String.join("\n", texts);
    sender.sendMessage(text);*/
    texts.forEach(text -> MessageHelper.sendMessage(sender, text));
  }

  public static class MessageBuilder {

    String text;

    public MessageBuilder withText(String text) {
      this.text = text;
      return this;
    }

    public MessageBuilder withField(String field, String replacer) {
      this.text = StringUtils.replace(this.text, field, replacer);
      return this;
    }

    public String build() {
      return this.text;
    }
  }
}
