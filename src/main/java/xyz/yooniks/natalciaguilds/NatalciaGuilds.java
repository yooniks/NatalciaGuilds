package xyz.yooniks.natalciaguilds;

import xyz.yooniks.natalciaguilds.api.GuildManager;

public interface NatalciaGuilds {

  //what about something like BasicManager<.., ..>, implementing managers with it and return managers in set?
  GuildManager getGuildManager();

}
