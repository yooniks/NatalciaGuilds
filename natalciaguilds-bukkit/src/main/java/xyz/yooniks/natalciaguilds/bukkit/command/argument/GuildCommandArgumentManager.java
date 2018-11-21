package xyz.yooniks.natalciaguilds.bukkit.command.argument;

import java.util.List;
import javax.annotation.Nullable;

public interface GuildCommandArgumentManager {

  @Nullable
  GuildCommandArgument findByName(String name);

  List<GuildCommandArgument> getAllArguments();

  void addArgument(GuildCommandArgument commandArgument);

}
