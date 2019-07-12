package xyz.yooniks.natalciaguilds.bukkit.listener;

import java.util.concurrent.TimeUnit;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import xyz.yooniks.natalciaguilds.api.guild.Guild;
import xyz.yooniks.natalciaguilds.api.user.User;
import xyz.yooniks.natalciaguilds.api.user.UserManager;
import xyz.yooniks.natalciaguilds.bukkit.config.MessagesConfig;
import xyz.yooniks.natalciaguilds.bukkit.guild.GuildManagerBukkit;
import xyz.yooniks.natalciaguilds.bukkit.guild.area.GuildAreaBukkit;
import xyz.yooniks.natalciaguilds.bukkit.helper.MessageHelper;
import xyz.yooniks.natalciaguilds.bukkit.helper.MessageHelper.MessageBuilder;
import xyz.yooniks.natalciaguilds.guild.GuildFlags;
import xyz.yooniks.natalciaguilds.guild.member.permission.GuildPermissions;

public class PlayerInteractAtCuboidListener implements Listener {

  private final UserManager userManager;
  private final GuildManagerBukkit guildManager;

  public PlayerInteractAtCuboidListener(UserManager userManager,
      GuildManagerBukkit guildManager) {
    this.userManager = userManager;
    this.guildManager = guildManager;
  }

  @EventHandler
  public void onBreak(BlockBreakEvent event) {
    final Block block = event.getBlock();
    final Guild guild = this.guildManager.findByLocation(block.getLocation());
    if (guild != null) {
      final Player player = event.getPlayer();

      final User user = this.userManager.createUser(player.getUniqueId());

      if (guild.isMember(user)) {
        if (!guild.hasPermission(user, GuildPermissions.BREAK_BLOCKS)) {
          event.setCancelled(true);
          MessageHelper
              .sendMessage(player, MessagesConfig.GUILD$CANNOT_BREAK$DOES_NOT_HAVE_PERMISSION);
          return;
        }
        if (block.getType() == Material.SPONGE) {
          event.setCancelled(true);
          MessageHelper.sendMessage(player, MessagesConfig.GUILD$CANNOT_BREAK$HEART_OF_GUILD);
        }
        return;
      }
      if (user.getGuild() != null && block.getType() == Material.SPONGE) {
        event.setCancelled(true);

        final long lastAttackTime = guild.getInfo().getFlag(GuildFlags.LAST_ATTACK_TIME);
        if (lastAttackTime + TimeUnit.DAYS.toMillis(1) > System.currentTimeMillis()) {
          MessageHelper.sendMessage(player, MessagesConfig.GUILD$CANNOT_ATTACK$WAIT);
          return;
        }
        int lives = guild.getInfo().getFlag(GuildFlags.LIVES);
        guild.getInfo().setFlag(GuildFlags.LIVES, lives -= 1);

        if (lives <= 0) {
          Bukkit.broadcastMessage(MessageHelper.colored(new MessageBuilder()
              .withText(MessagesConfig.GUILD$CANNOT_ATTACK$DESTROYED)
              .withField("%destroyed_guild%", guild.getTag())
              .withField("%attacking_guild%", user.getGuild().getTag())
              .build()
          ));
          ((GuildAreaBukkit) guild.getArea()).getCenter().getBlock()
              .setType(Material.DIAMOND_BLOCK);
          this.guildManager.removeGuild(guild);
        }
        return;
      }
      MessageHelper.sendMessage(player, MessagesConfig.GUILD$CANNOT_BREAK$NOT_MEMBER);
    }
  }

  @EventHandler
  public void onPlace(BlockPlaceEvent event) {
    final Block block = event.getBlock();
    final Guild guild = this.guildManager.findByLocation(block.getLocation());
    if (guild != null) {
      final Player player = event.getPlayer();

      final User user = this.userManager.createUser(player.getUniqueId());
      if (guild.isMember(user)) {
        if (!guild.hasPermission(user, GuildPermissions.PLACE_BLOCKS)) {
          event.setCancelled(true);
          MessageHelper.sendMessage(player, MessagesConfig.GUILD$CANNOT_BREAK$NOT_MEMBER);
        }
        return;
      }
      event.setCancelled(true);
      MessageHelper.sendMessage(player, MessagesConfig.GUILD$CANNOT_PLACE$NOT_MEMBER);
    }
  }

}
