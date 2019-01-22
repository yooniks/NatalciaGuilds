package xyz.yooniks.natalciaguilds.bukkit.helper;

import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class PlayerHelper {

  public static boolean hasItems(Player player, List<ItemStack> items) {
    return !items.stream()
        .allMatch(item -> player.getInventory().containsAtLeast(item, item.getAmount()));
  }

  private PlayerHelper() {
  }

}
