package xyz.yooniks.natalciaguilds.bukkit.config;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import xyz.yooniks.natalciaguilds.bukkit.config.system.Config;

public class MessagesConfig extends Config {

  public static String GUILD$CREATE$SUCCESS_BROADCAST = "&2[Gildie] &7Gracz &6%name% &7zalozyl gildie! &8(&7Tag: &6%tag%&7, nazwa: &6%name%&8)";
  public static String GUILD$CREATE$ERROR_ITEMS = "&2[Gildie] &cNie masz wystarczajaco duzo itemow do zalozenia gildii! Potrzebujesz: &6%items%";
  public static String GUILD$REMOVE$SUCCESS_BROADCAST = "&2[Gildie] &7Gracz &6%name% &7usunal gildie o tagu &6%tag%&7!";
  public static List<String> COMMAND$ARGUMENT_LIST = Arrays.asList(
      "&2[GILDIE]",
      "&6/g zaloz [tag] [nazwa] &7- stworz swoja wlasna gildie!",
      "&2[GILDIE]"
  );

  public static String GUILD$CANNOT_ATTACK$WAIT = "&2[Gildie] &cTa gildia zostala juz zaatakowana w ciagu 24 godzin!";
  public static String GUILD$CANNOT_ATTACK$DESTROYED = "&2[Gildie] &cGildia &6%destroyed_guild% &czostala podbita przez &6%attacking_guild%&c! GRATULACJE!";

  public static String GUILD$WAR$REMOVED_LIVE = "&2[Gildie] &cGildia &6%attacking_guild% &codebrala jedno zycie gildii &6%victim_guild%";

  public static String GUILD$CANNOT_BREAK$NOT_MEMBER = "&2[Gildie] &cNie mozesz niszczyc blokow na terenie gildii!";
  public static String GUILD$CANNOT_BREAK$DOES_NOT_HAVE_PERMISSION = "&2[Gildie] &cNie masz uprawnien do niszczenia blokow w gildii!";
  public static String GUILD$CANNOT_BREAK$HEART_OF_GUILD = "&2[Gildie] &cNie mozesz zniszczyc serca swojej gildii!";

  public static String GUILD$CANNOT_PLACE$NOT_MEMBER = "&2[Gildie] &cNie mozesz klasc blokow na terenie gildii!";
  public static String GUILD$CANNOT_PLACE$DOES_NOT_HAVE_PERMISSION = "&2[Gildie] &cNie masz uprawnien do stawiania blokow w gildii!";

  public MessagesConfig(File file, Class<?> clazz) {
    super(file, clazz);
  }

}
