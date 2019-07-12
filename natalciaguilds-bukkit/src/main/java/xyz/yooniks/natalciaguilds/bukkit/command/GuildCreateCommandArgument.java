package xyz.yooniks.natalciaguilds.bukkit.command;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.yooniks.natalciaguilds.api.command.GuildCommandArgumentInfo;
import xyz.yooniks.natalciaguilds.api.guild.Guild;
import xyz.yooniks.natalciaguilds.api.guild.GuildManager;
import xyz.yooniks.natalciaguilds.api.guild.area.GuildArea;
import xyz.yooniks.natalciaguilds.api.guild.area.GuildAreaCreator;
import xyz.yooniks.natalciaguilds.api.guild.member.GuildMember;
import xyz.yooniks.natalciaguilds.api.user.UserManager;
import xyz.yooniks.natalciaguilds.bukkit.config.MessagesConfig;
import xyz.yooniks.natalciaguilds.bukkit.config.SettingsConfig;
import xyz.yooniks.natalciaguilds.bukkit.guild.area.GuildAreaImpl;
import xyz.yooniks.natalciaguilds.bukkit.guild.area.creator.DefaultGuildAreaCreator;
import xyz.yooniks.natalciaguilds.bukkit.helper.MessageHelper;
import xyz.yooniks.natalciaguilds.bukkit.helper.MessageHelper.MessageBuilder;
import xyz.yooniks.natalciaguilds.bukkit.helper.PlayerHelper;
import xyz.yooniks.natalciaguilds.guild.GuildImpl;
import xyz.yooniks.natalciaguilds.guild.member.permission.GuildPermissions;

@GuildCommandArgumentInfo(
    names = {"zaloz", "create"},
    usage = "zaloz [tag] [nazwa]"
)
public class GuildCreateCommandArgument implements GuildCommandArgumentExecutor {

  private final List<ItemStack> requiredItems;
  private final String requiredItemsMessage;

  private final UserManager userManager;
  private final GuildManager guildManager;

  private final GuildAreaCreator areaCreator = new DefaultGuildAreaCreator();

  public GuildCreateCommandArgument(List<ItemStack> requiredItems,
      UserManager userManager, GuildManager guildManager) {
    this.requiredItems = requiredItems;
    this.userManager = userManager;
    this.guildManager = guildManager;

    this.requiredItemsMessage = StringUtils.join(this.requiredItems.stream()
        .map(item -> item.getType().name().toLowerCase() + " x" + item.getAmount())
        .collect(Collectors.toList()), ",");
  }


  @Override
  public void execute(CommandSender sender, String[] args) {
    final Player player = (Player) sender;

    final String tag = args[0];
    final String name = args[1];
    if (tag.length() < 2 || tag.length() > 4) {
      MessageHelper.sendMessage(player, "&cTag gildii jest zbyt dlugi lub krotki!");
      return;
    }
    if (name.length() < 6 || name.length() > 16) {
      MessageHelper.sendMessage(player, "&cNazwa gildii jest zbyt dluga lub krotka!");
      return;
    }
    if (!PlayerHelper.hasItems(player, this.requiredItems)) {
      MessageHelper.sendMessage(player, new MessageBuilder()
          .withText(MessagesConfig.GUILD$CREATE$ERROR_ITEMS)
          .withField("%items%", this.requiredItemsMessage));
      return;
    }
    final GuildMember user = this.userManager.createUser(player.getUniqueId());

    final GuildArea area = new GuildAreaImpl(player.getLocation(),
        SettingsConfig.GUILD$CREATE$START_SIZE);

    final Set<GuildMember> members = new HashSet<>(Collections.singletonList(user));

    final Guild guild = new GuildImpl(tag, name, area, members);
    guild.addPermission(user, GuildPermissions.OWNER);
    this.areaCreator.createCenter(area);
    this.guildManager.createGuild(guild);

    MessageHelper.sendMessage(player, new MessageBuilder()
        .withText(MessagesConfig.GUILD$CREATE$SUCCESS_BROADCAST)
        .withField("%tag%", guild.getTag())
        .withField("%name%", guild.getName())
        .withField("%player%", player.getName()));
  }

}
