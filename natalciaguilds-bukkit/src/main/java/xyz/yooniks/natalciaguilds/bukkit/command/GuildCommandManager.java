package xyz.yooniks.natalciaguilds.bukkit.command;

import java.util.List;
import javax.annotation.Nullable;
import org.bukkit.command.Command;

public interface GuildCommandManager {

  @Nullable
  GuildCommandArgument findByName(String name);

  List<GuildCommandArgument> getAllArguments();

  void registerCommand(Command command);

  void addGuildCommandArgument(GuildCommandArgumentExecutor executableArgument);

}
