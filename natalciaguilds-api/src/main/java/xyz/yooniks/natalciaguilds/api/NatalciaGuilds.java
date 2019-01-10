package xyz.yooniks.natalciaguilds.api;

import xyz.yooniks.natalciaguilds.api.guild.GuildManager;
import xyz.yooniks.natalciaguilds.api.user.UserManager;

public interface NatalciaGuilds {

  GuildManager getGuildManager();

  UserManager getUserManager();

}
