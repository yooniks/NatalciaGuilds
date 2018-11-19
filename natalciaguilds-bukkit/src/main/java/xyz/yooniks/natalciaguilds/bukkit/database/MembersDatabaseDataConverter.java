package xyz.yooniks.natalciaguilds.bukkit.database;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.apache.commons.lang.StringUtils;
import xyz.yooniks.natalciaguilds.api.database.DatabaseDataConverter;
import xyz.yooniks.natalciaguilds.api.guild.member.GuildMember;
import xyz.yooniks.natalciaguilds.api.guild.member.GuildMemberBuilder;
import xyz.yooniks.natalciaguilds.api.guild.member.permission.GuildPermission;
import xyz.yooniks.natalciaguilds.api.guild.member.permission.GuildPermissions;

public class MembersDatabaseDataConverter implements
    DatabaseDataConverter<String, Set<GuildMember>> {

  //todo poprawic bo zle gowno robi
  @Override
  public Set<GuildMember> fromDatabaseColumn(String string) {
    return Arrays.stream(string.split(";"))
        .map(this::fromStringToGuildMember)
        .collect(Collectors.toSet());
  }

  @Override
  public String toDatabaseColumn(Set<GuildMember> guildMembers) {
    return guildMembers
        .stream()
        .map(this::guildMemberToString)
        .collect(Collectors.joining(";"));
  }

  private String guildMemberToString(GuildMember member) {
    /*return member.getIdentifier().toString() + ";" + member.getPermissions()
        .stream()
        .map(perm -> String.valueOf(perm.getId()))

        .collect(Collectors.joining(","));*/
    return "";
  }

  private GuildMember fromStringToGuildMember(String string) {
    final UUID uuid = UUID.fromString(string);
    final String[] permissionsInString = string.split(",");
    final Set<GuildPermission> permissions = Arrays.stream(permissionsInString)
        .filter(StringUtils::isNumeric)
        .map(Integer::parseInt)
        .map(GuildPermissions::byId)
        .collect(Collectors.toSet());
    //return new GuildMemberImpl(uuid, permissions);
    return new GuildMemberBuilder()
        .withIdentifier(uuid)
        .build();
  }

}
