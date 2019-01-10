package xyz.yooniks.natalciaguilds.bukkit.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import xyz.yooniks.natalciaguilds.api.user.User;
import xyz.yooniks.natalciaguilds.api.user.UserManager;

public class UserManagerImpl implements UserManager {

  private final Map<UUID, User> userMap = new HashMap<>();

  @Override
  public User createUser(UUID uuid) {
    User user = this.userMap.get(uuid);
    if (user == null) {
      this.userMap.put(uuid, user = new UserImpl(uuid));
    }
    return user;
  }

  @Override
  public void removeUser(UUID uuid) {
    this.userMap.remove(uuid);
  }

  @Override
  public List<User> getAll() {
    return null;
  }

}
