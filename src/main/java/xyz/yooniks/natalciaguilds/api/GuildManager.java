package xyz.yooniks.natalciaguilds.api;

import javax.annotation.Nullable;

public interface GuildManager {

  @Nullable
  Guild findByTag(String tag);

}
