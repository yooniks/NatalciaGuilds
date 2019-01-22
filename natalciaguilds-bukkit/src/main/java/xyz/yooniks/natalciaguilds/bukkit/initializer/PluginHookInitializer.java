package xyz.yooniks.natalciaguilds.bukkit.initializer;

import java.util.Objects;
import org.reflections.Reflections;
import xyz.yooniks.natalciaguilds.bukkit.hook.PluginHook;

public class PluginHookInitializer implements Initializer {

  @Override
  public void initialize() {
    //or just simple list instead of this xd
    final Reflections reflections = new Reflections("xyz.yooniks.natalciaguilds.bukkit.hook");
    reflections.getSubTypesOf(PluginHook.class)
        .stream()
        .map(clazz -> {
          try {
            return (PluginHook) clazz.newInstance();
          } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
          }
          return null;
        })
        .filter(hook -> Objects.nonNull(hook) && hook.shouldBeLoaded())
        .forEach(PluginHook::initialize);
  }

}
