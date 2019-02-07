package xyz.yooniks.natalciaguilds.bukkit.command;

public class GuildCommandArgument {

  private final String[] names;
  private final String usage;
  private final boolean playerOnly;
  private final int minArgs;

  private final GuildCommandArgumentExecutor executor;

  public GuildCommandArgument(String[] names, String usage, boolean playerOnly, int minArgs,
      GuildCommandArgumentExecutor executor) {
    this.names = names;
    this.usage = usage;
    this.playerOnly = playerOnly;
    this.minArgs = minArgs;
    this.executor = executor;
  }

  public String[] getNames() {
    return names;
  }

  public String getUsage() {
    return usage;
  }

  public boolean isPlayerOnly() {
    return playerOnly;
  }

  public int getMinArgs() {
    return minArgs;
  }

  public GuildCommandArgumentExecutor getExecutor() {
    return executor;
  }

}
