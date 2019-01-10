package xyz.yooniks.natalciaguilds.bukkit.initializer.impl;

import org.reflections.Reflections;
import xyz.yooniks.natalciaguilds.bukkit.command.arg.basic.GuildCommandArgumentExecutor;
import xyz.yooniks.natalciaguilds.bukkit.command.basic.GuildCommandManager;
import xyz.yooniks.natalciaguilds.bukkit.initializer.Initializer;

public class CommandInitializer implements Initializer {

  private final GuildCommandManager commandManager;

  public CommandInitializer(
      GuildCommandManager commandManager) {
    this.commandManager = commandManager;
  }

  @Override
  public void initialize() {
    final Reflections reflections = new Reflections(
        "xyz.yooniks.natalciaguilds.bukkit.command.basic.impl");
    reflections.getSubTypesOf(GuildCommandArgumentExecutor.class)
        .forEach(this.commandManager::addGuildCommandArgument);
  }

}
