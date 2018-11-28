package xyz.yooniks.natalciaguilds.bukkit.config;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import xyz.yooniks.natalciaguilds.bukkit.config.system.Config;

public class MessagesConfig extends Config {

  public MessagesConfig(File file, Class<?> clazz) {
    super(file, clazz);
  }

  public static String GUILD$CREATE$SUCCESS_BROADCAST = "&2[Gildie] &7Gracz &6%name% &7zalozyl gildie! &8(&7Tag: &6%tag%&7, nazwa: &6%name%&8)";
  public static String GUILD$CREATE$ERROR_ITEMS = "&2[Gildie] &cNie masz wystarczajaco duzo itemow do zalozenia gildii! Potrzebujesz: &6%items%";

  public static String GUILD$REMOVE$SUCCESS_BROADCAST = "&2[Gildie] &7Gracz &6%name% &7usunal gildie o tagu &6%tag%&7!";

  public static List<String> COMMAND$ARGUMENT_LIST = Arrays.asList(
      "&2[GILDIE]",
      "&6/g zaloz [tag] [nazwa] &7- stworz swoja wlasna gildie!",
      "&2[GILDIE]"
  );

}
