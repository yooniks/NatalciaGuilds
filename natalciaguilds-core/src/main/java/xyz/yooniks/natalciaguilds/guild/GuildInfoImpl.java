package xyz.yooniks.natalciaguilds.guild;

import java.util.HashMap;
import java.util.Map;
import xyz.yooniks.natalciaguilds.api.guild.GuildInfo;

public class GuildInfoImpl implements GuildInfo {

  private final Map<String, Object> flags = new HashMap<>();

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getFlag(String flagName) {
    return (T) this.flags.get(flagName);
  }

  @Override
  public void setFlag(String flagName, Object value) {
    this.flags.put(flagName, value);
  }

}
