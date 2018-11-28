package xyz.yooniks.natalciaguilds.bukkit.command.basic;

import java.util.List;
import javax.annotation.Nullable;
import org.bukkit.command.Command;

public interface GuildCommandManager {

  @Nullable
  GuildCommandArgument findByName(String name);

  List<GuildCommandArgument> getAllArguments();

  void setCommand(Command command);

  void addArgument(Class<? extends GuildCommandArgumentExecutor> executableGuildCommand);

}
