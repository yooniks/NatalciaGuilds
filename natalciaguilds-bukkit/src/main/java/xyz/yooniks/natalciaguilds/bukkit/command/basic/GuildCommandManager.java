package xyz.yooniks.natalciaguilds.bukkit.command.basic;

import java.util.List;
import javax.annotation.Nullable;
import org.bukkit.command.Command;
import xyz.yooniks.natalciaguilds.bukkit.command.arg.basic.GuildCommandArgument;
import xyz.yooniks.natalciaguilds.bukkit.command.arg.basic.GuildCommandArgumentExecutor;

public interface GuildCommandManager {

  @Nullable
  GuildCommandArgument findByName(String name);

  List<GuildCommandArgument> getAllArguments();

  void registerCommand(Command command);

  void addGuildCommandArgument(
      Class<? extends GuildCommandArgumentExecutor> executableGuildCommand);

}
