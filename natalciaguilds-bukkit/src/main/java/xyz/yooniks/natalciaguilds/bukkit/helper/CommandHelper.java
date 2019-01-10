package xyz.yooniks.natalciaguilds.bukkit.helper;

import java.lang.reflect.Field;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;

public final class CommandHelper {

  private static CommandMap commandMap;

  //maybe i will make something else but now it's the best solution
  static {
    final Field field;
    try {
      field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
      field.setAccessible(true);
      commandMap = (CommandMap) field.get(Bukkit.getServer());
    } catch (ReflectiveOperationException ex) {
      ex.printStackTrace();
    }
  }

  private CommandHelper() {
  }

  public static void registerCommand(String commandName, Command command) {
    commandMap.register(commandName, command);
  }

}
