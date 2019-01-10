package xyz.yooniks.natalciaguilds.bukkit.command.arg;

import org.bukkit.command.CommandSender;
import xyz.yooniks.natalciaguilds.api.command.GuildCommandArgumentInfo;
import xyz.yooniks.natalciaguilds.bukkit.command.arg.basic.GuildCommandArgumentExecutor;

@GuildCommandArgumentInfo(
    names = {"zaloz", "create"},
    usage = "zaloz [tag] [nazwa]"
)
public class GuildCreateCommandArgument implements GuildCommandArgumentExecutor {

  @Override
  public void execute(CommandSender sender, String[] args) {

    final String tag = args[0];
    final String name = args[1];
    if (tag.length() < 2 || tag.length() > 4 || name.length() < 6 || name.length() > 16) {

      return;
    }
  }

}
