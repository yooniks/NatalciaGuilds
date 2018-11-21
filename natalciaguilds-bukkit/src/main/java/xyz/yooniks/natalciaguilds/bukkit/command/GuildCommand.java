package xyz.yooniks.natalciaguilds.bukkit.command;

import java.util.Arrays;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import xyz.yooniks.natalciaguilds.bukkit.command.argument.GuildCommandArgument;
import xyz.yooniks.natalciaguilds.bukkit.command.argument.GuildCommandArgumentManager;
import xyz.yooniks.natalciaguilds.bukkit.helper.MessageHelper;

public class GuildCommand extends Command {

  private final GuildCommandArgumentManager commandArgumentManager;

  public GuildCommand(GuildCommandArgumentManager commandArgumentManager, String name,
      String description, String usageMessage, List<String> aliases) {
    super(name, description, usageMessage, aliases);
    this.commandArgumentManager = commandArgumentManager;
  }

  @Override
  public boolean execute(CommandSender sender, String commandLabel, String[] args) {
    if (args.length == 0) {
      //if I add configs i will change this
      sender.sendMessage("Lista argumentow: ..." + this.commandArgumentManager.getAllArguments().toString());
      return true;
    }
    final String commandArgumentName = args[0];
    final GuildCommandArgument commandArgument = this.commandArgumentManager.findByName(commandArgumentName);
    if (commandArgument == null) {
      MessageHelper.sendMessage(sender, "&cArgument &6" + commandArgumentName + " &cnie istnieje! &6/g");
      return true;
    }
    commandArgument.execute(sender, Arrays.copyOfRange(args, 1, args.length));
    return true;
  }

}
