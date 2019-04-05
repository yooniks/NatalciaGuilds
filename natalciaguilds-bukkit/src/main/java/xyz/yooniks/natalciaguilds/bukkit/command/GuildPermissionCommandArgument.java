package xyz.yooniks.natalciaguilds.bukkit.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.yooniks.natalciaguilds.api.command.GuildCommandArgumentInfo;
import xyz.yooniks.natalciaguilds.api.guild.Guild;
import xyz.yooniks.natalciaguilds.api.guild.member.GuildMember;
import xyz.yooniks.natalciaguilds.api.guild.member.permission.GuildPermission;
import xyz.yooniks.natalciaguilds.api.guild.member.permission.GuildPermissions;
import xyz.yooniks.natalciaguilds.api.user.User;
import xyz.yooniks.natalciaguilds.api.user.UserManager;
import xyz.yooniks.natalciaguilds.bukkit.helper.MessageHelper;

@GuildCommandArgumentInfo(
    names = {"permission", "permisje", "permisja", "permissions"},
    usage = "permission [add/remove/list] [nick czlonka gildii] [id permisjii]",
    minArgs = 1
)
public class GuildPermissionCommandArgument implements GuildCommandArgumentExecutor {

  private final UserManager userManager;

  public GuildPermissionCommandArgument(UserManager userManager) {
    this.userManager = userManager;
  }

  @Override
  public void execute(CommandSender sender, String[] args) {
    final Player player = (Player) sender;
    final User user = this.userManager.createUser(player.getUniqueId());
    final Guild guild = user.getGuild();
    if (guild == null) {
      MessageHelper.sendMessage(sender,
          "&cNie jestes w zadnej gildii, nie mozesz sprawdzac permisjii swoich czlonkow!");
      return;
    }

    if (args.length >= 1 && args[0].equalsIgnoreCase("list")) {

      final GuildMember member;

      if (args.length > 1) {
        final String targetName = args[1];
        member = guild.findMemberByName(targetName);
        if (member == null) {
          MessageHelper.sendMessage(sender,
              "&cW twojej gildii nie ma gracza o nicku &6" + targetName + "&c!");
          return;
        }
      } else {
        member = user;
      }
      MessageHelper.sendMessage(sender,
          "&cLista permisjii czlonka &6" + Bukkit.getOfflinePlayer(member.getIdentifier())
              .getName());
      GuildPermissions.PERMISSIONS.stream()
          .filter(permission -> guild.hasPermission(member, permission))
          .forEach(permission -> MessageHelper.sendMessage(sender,
              "&cId permisjii: &6" + permission.getId() + "&c, nazwa: &6" + permission.getName()));

    } else if (args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("remove")) {

      final String targetName = args[1];
      final GuildMember targetMember = guild.findMemberByName(targetName);
      if (targetMember == null) {
        MessageHelper
            .sendMessage(sender, "&cW twojej gildii nie ma gracza o nicku &6" + targetName + "&c!");
        return;
      }
      final int permissionId;
      try {
        permissionId = Integer.parseInt(args[2]);
      } catch (NumberFormatException ex) {
        MessageHelper.sendMessage(sender, "&cArgument &6" + args[2] + " &cnie jest liczba!");
        return;
      }

      final GuildPermission permission = GuildPermissions.byId(permissionId);
      if (permission == null) {
        MessageHelper.sendMessage(sender, "&cPermisja z id &6" + permissionId
            + "&c nie istnieje! Lista permisjii: &6/g permission list");
        return;
      }

      if (!permission.isRemovable()) {
        MessageHelper.sendMessage(sender, "&cPermisja z id &6" + permissionId + " "
            + "&cnie moze byc nadawania ani usuwana!");
        return;
      }

      if (args[0].equalsIgnoreCase("add")) {
        if (guild.hasPermission(targetMember, permission)) {
          MessageHelper.sendMessage(sender, "&cTen gracz ma juz taka permisje!");
          return;
        }
        guild.addPermission(targetMember, permission);
        MessageHelper.sendMessage(sender,
            "&cGracz &6" + targetName + " &cotrzymal permisje &6" + permission.getName());
      } else {
        if (!guild.hasPermission(targetMember, permission)) {
          MessageHelper.sendMessage(sender, "&cTen gracz nie ma takiej permisjii!");
          return;
        }
        guild.removePermission(targetMember, permission);
        MessageHelper.sendMessage(sender,
            "&cGracz &6" + targetName + " &czostal pozbawiony permisjii &6" + permission.getName());
      }
    }
  }

}
