package xyz.yooniks.natalciaguilds.api.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserManager {

  //never null
  User createUser(UUID uuid);

  void removeUser(UUID uuid);

  Optional<User> findByIdentifier(UUID identifier);

  List<User> getAll();

}
