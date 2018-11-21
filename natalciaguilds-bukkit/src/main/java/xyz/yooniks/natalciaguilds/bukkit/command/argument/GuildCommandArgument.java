package xyz.yooniks.natalciaguilds.bukkit.command.argument;

import org.bukkit.command.CommandSender;

public interface GuildCommandArgument {

  void execute(CommandSender sender, String[] args);

  String[] getNames();

}
