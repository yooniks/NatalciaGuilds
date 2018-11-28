package xyz.yooniks.natalciaguilds.bukkit.command.basic.impl;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import org.apache.commons.lang.Validate;
import org.bukkit.command.Command;
import xyz.yooniks.natalciaguilds.bukkit.command.basic.GuildCommandArgument;
import xyz.yooniks.natalciaguilds.api.command.GuildCommandArgumentInfo;
import xyz.yooniks.natalciaguilds.bukkit.command.basic.GuildCommandArgumentExecutor;
import xyz.yooniks.natalciaguilds.bukkit.command.basic.GuildCommandManager;
import xyz.yooniks.natalciaguilds.bukkit.helper.CommandHelper;

public class GuildCommandManagerImpl implements GuildCommandManager {

  private final List<GuildCommandArgument> arguments = new ArrayList<>();

  @Nullable
  @Override
  public GuildCommandArgument findByName(String name) {
    return this.arguments.stream()
        .filter(argument -> Arrays.asList(argument.getNames()).contains(name.toLowerCase()))
        .findFirst()
        .orElse(null);
  }

  @Override
  public void setCommand(Command command) {
    CommandHelper.registerCommand("guild", command);
  }

  @Override
  public void addArgument(Class<? extends GuildCommandArgumentExecutor> executableGuildCommand) {
    final GuildCommandArgumentInfo argumentInfo = executableGuildCommand.getAnnotation(GuildCommandArgumentInfo.class);
    Validate.notNull(argumentInfo, "Annonation with command info cannot be null!");

    try {
      final GuildCommandArgumentExecutor executor = executableGuildCommand
          .getDeclaredConstructor().newInstance();

      final GuildCommandArgument commandArgument = new GuildCommandArgument(
          argumentInfo.names(), argumentInfo.usage(),
          argumentInfo.playerOnly(), argumentInfo.minArgs(),
          executor
      );

      arguments.add(commandArgument);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Override
  public List<GuildCommandArgument> getAllArguments() {
    return ImmutableList.copyOf(this.arguments);
  }

}

