package xyz.yooniks.natalciaguilds.bukkit.command.argument.impl;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import xyz.yooniks.natalciaguilds.bukkit.command.argument.GuildCommandArgument;
import xyz.yooniks.natalciaguilds.bukkit.command.argument.GuildCommandArgumentManager;

public class GuildCommandArgumentManagerImpl implements GuildCommandArgumentManager {

  private final List<GuildCommandArgument> argumentList = new ArrayList<>();

  @Nullable
  @Override
  public GuildCommandArgument findByName(String name) {
    return this.argumentList.stream()
        .filter(argument -> Arrays.asList(argument.getNames()).contains(name.toLowerCase()))
        .findFirst()
        .orElse(null);
  }

  @Override
  public void addArgument(GuildCommandArgument commandArgument) {
    this.argumentList.add(commandArgument);
  }

  @Override
  public List<GuildCommandArgument> getAllArguments() {
    return ImmutableList.copyOf(this.argumentList);
  }

}

