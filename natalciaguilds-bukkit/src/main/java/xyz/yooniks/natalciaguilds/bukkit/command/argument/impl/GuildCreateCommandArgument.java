package xyz.yooniks.natalciaguilds.bukkit.command.argument.impl;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.yooniks.natalciaguilds.bukkit.command.argument.GuildCommandArgument;
import xyz.yooniks.natalciaguilds.bukkit.helper.MessageHelper;

public class GuildCreateCommandArgument implements GuildCommandArgument {

  private final String[] names;

  public GuildCreateCommandArgument(String... names) {
    this.names = names;
  }

  @Override
  public void execute(CommandSender sender, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("Only player");
      return;
    }

    if (args.length == 0) {

      return;
    }
    final String tag = args[0];
    final String name = args[1];
    if (tag.length() < 2 || tag.length() > 4 || name.length() < 6 || name.length() > 16) {

      return;
    }
  }

  @Override
  public String[] getNames() {
    return this.names;
  }

}
