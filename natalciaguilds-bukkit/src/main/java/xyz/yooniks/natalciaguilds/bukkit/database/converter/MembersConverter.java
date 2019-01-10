package xyz.yooniks.natalciaguilds.bukkit.database.converter;

import com.google.gson.Gson;
import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import xyz.yooniks.natalciaguilds.api.database.DatabaseDataConverter;
import xyz.yooniks.natalciaguilds.api.guild.member.GuildMember;
import xyz.yooniks.natalciaguilds.api.guild.member.GuildMemberBuilder;
import xyz.yooniks.natalciaguilds.api.ranking.Ranking;
import xyz.yooniks.natalciaguilds.api.ranking.impl.UserRanking;

public class MembersConverter implements DatabaseDataConverter<String, Set<GuildMember>> {

  private static final Gson GSON = new Gson();

  @Override
  public Set<GuildMember> fromDatabaseColumn(String strings) {
    return Arrays.stream(strings.split("-"))
        .map(this::memberFromString)
        .collect(Collectors.toSet());
  }

  @Override
  public String toDatabaseColumn(Set<GuildMember> guildMembers) {
    return guildMembers.stream()
        .map(this::memberToString)
        .collect(Collectors.joining("-"));
  }

  private String memberToString(GuildMember member) {
    return member.getIdentifier().toString() + ";" + member.getRanking().toString();
  }

  private GuildMember memberFromString(String string) {
    final String[] split = string.split(";");
    final UUID uuid = UUID.fromString(split[0]);
    final Ranking ranking = GSON.fromJson(split[1],
        UserRanking.class); //or just make ranking configurationserializable etc..
    return new GuildMemberBuilder()
        .withIdentifier(uuid)
        .withRanking(ranking)
        .build();
  }

}
