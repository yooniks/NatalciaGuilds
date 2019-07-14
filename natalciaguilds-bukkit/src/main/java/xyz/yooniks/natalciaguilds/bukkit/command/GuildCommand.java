package xyz.yooniks.natalciaguilds.bukkit.command;

import java.util.Arrays;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.yooniks.natalciaguilds.bukkit.config.MessagesConfig;
import xyz.yooniks.natalciaguilds.bukkit.helper.MessageHelper;

public class GuildCommand extends Command {

  private final GuildCommandManager commandArgumentManager;

  public GuildCommand(GuildCommandManager commandArgumentManager) {
    super("guild", "Glowna komenda od gildii", "/g",
        Arrays.asList("guilds", "gildie", "factions", "g", "funnyguilds"));
    this.commandArgumentManager = commandArgumentManager;
  }

  @Override
  public boolean execute(CommandSender sender, String commandLabel, String[] args) {
    if (args.length == 0) {
      MessageHelper.sendMessage(sender, MessagesConfig.COMMAND$ARGUMENT_LIST);
      return true;
    }
    final String commandArgumentName = args[0];
    final GuildCommandArgument commandArgument = this.commandArgumentManager
        .findByName(commandArgumentName);
    if (commandArgument == null) {
      MessageHelper
          .sendMessage(sender, "&cArgument &6" + commandArgumentName + " &cnie istnieje! &6/g");
      return true;
    }

    final String[] changedArgs = Arrays.copyOfRange(args, 1, args.length);
    if (changedArgs.length < commandArgument.getMinArgs()) {
      MessageHelper.sendMessage(sender, "&7Poprawne uzycie: &6/g " + commandArgument.getUsage());
      return true;
    }

    if (commandArgument.isPlayerOnly() && !(sender instanceof Player)) {
      sender.sendMessage("This command is executable only by player");
      return true;
    }

    commandArgument.getExecutor().execute(sender, Arrays.copyOfRange(args, 1, args.length));
    return true;
  }

}
