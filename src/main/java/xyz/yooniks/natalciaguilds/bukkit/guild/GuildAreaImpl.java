package xyz.yooniks.natalciaguilds.bukkit.guild;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import xyz.yooniks.natalciaguilds.api.guild.GuildArea;
import xyz.yooniks.natalciaguilds.api.guild.member.GuildMember;
import xyz.yooniks.natalciaguilds.bukkit.helper.GuildHelper;

public class GuildAreaImpl implements GuildArea {

  private final Location center;
  private int size;

  public GuildAreaImpl(Location center, int size) {
    this.center = center;
    this.size = size;
    this.update();
  }

  @Override
  public void update() {
  }

  @Override
  public boolean isInArea(GuildMember member) {
    final Player player = Bukkit.getPlayer(member.getIdentifier());
    if (player == null) {
      return false;
    }
    return GuildHelper.isInArea(this, this.getCenter(), player.getLocation());
  }

  @Override
  public int getSize() {
    return size;
  }

  @Override
  public void setSize(int size) {
    this.size = size;
  }

  public Location getCenter() {
    return center;
  }

}
