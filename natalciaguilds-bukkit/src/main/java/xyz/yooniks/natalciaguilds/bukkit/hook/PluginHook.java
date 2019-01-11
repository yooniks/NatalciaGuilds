package xyz.yooniks.natalciaguilds.bukkit.hook;

public interface PluginHook {

  boolean shouldBeLoaded();

  void initialize();

  void unload();

}
