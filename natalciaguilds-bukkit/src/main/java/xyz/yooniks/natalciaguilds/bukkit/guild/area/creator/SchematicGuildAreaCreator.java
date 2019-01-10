package xyz.yooniks.natalciaguilds.bukkit.guild.area.creator;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import xyz.yooniks.natalciaguilds.bukkit.guild.area.GuildAreaBukkit;

public class SchematicGuildAreaCreator implements GuildAreaCreator {

  @Override
  public void create(GuildAreaBukkit guildArea) {
    final Location center = guildArea.getCenter();
    center.getBlock().setType(Material.SPONGE);
    center.getWorld().playEffect(center, Effect.HEART, Effect.HEART.getData());
  }

}
