package xyz.yooniks.natalciaguilds.api.database;

import java.util.List;

public interface DatabaseDataManager<T> {

  T load(T t);

  List<T> findAll();

  void update(T t);

  void remove(T t);

}
