package xyz.yooniks.natalciaguilds.bukkit.guild;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import xyz.yooniks.natalciaguilds.api.GuildArea;
import xyz.yooniks.natalciaguilds.api.member.GuildMember;

public class GuildAreaImpl implements GuildArea {

  private int x, z;
  private int size;

  public GuildAreaImpl(int x, int z, int size) {
    this.x = x;
    this.z = z;
    this.size = size;
    this.update();
  }

  @Override
  public void update() {
  }

  @Override
  public int getSize() {
    return size;
  }

  @Override
  public boolean isInArea(GuildMember member) {
    final Player player = Bukkit.getPlayer(member.getIdentifier());
    if (player == null) {
      return false;
    }
    final Location location = player.getLocation();
    return false;
  }

}
