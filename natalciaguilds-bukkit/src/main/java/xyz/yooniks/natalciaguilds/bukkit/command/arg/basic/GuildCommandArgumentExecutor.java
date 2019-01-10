package xyz.yooniks.natalciaguilds.bukkit.command.arg.basic;

import org.bukkit.command.CommandSender;

public interface GuildCommandArgumentExecutor {

  void execute(CommandSender sender, String[] args);

}
