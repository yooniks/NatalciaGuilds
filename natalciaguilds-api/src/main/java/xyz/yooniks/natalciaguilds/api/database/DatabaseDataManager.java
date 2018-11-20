package xyz.yooniks.natalciaguilds.api.database;

import java.util.List;
import xyz.yooniks.natalciaguilds.api.guild.Guild;

public interface DatabaseDataManager<T> {

  List<T> findAll();

  void update(T t);

  void remove(T t);

}
