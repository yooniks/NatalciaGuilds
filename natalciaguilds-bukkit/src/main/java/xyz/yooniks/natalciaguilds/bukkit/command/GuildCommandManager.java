package xyz.yooniks.natalciaguilds.bukkit.command;

import java.util.List;
import javax.annotation.Nullable;

public interface GuildCommandManager {

  @Nullable
  GuildCommandArgument findByName(String name);

  List<GuildCommandArgument> getAllArguments();

  void addGuildCommandArgument(GuildCommandArgumentExecutor executableArgument);

}
