package xyz.yooniks.natalciaguilds.bukkit.initializer;

import xyz.yooniks.natalciaguilds.bukkit.command.arg.GuildCreateCommandArgument;
import xyz.yooniks.natalciaguilds.bukkit.command.basic.GuildCommandManager;

public class CommandInitializer implements Initializer {

  private final GuildCommandManager commandManager;

  public CommandInitializer(
      GuildCommandManager commandManager) {
    this.commandManager = commandManager;
  }

  @Override
  public void initialize() {
    this.commandManager.addGuildCommandArgument(new GuildCreateCommandArgument());
  }

}
