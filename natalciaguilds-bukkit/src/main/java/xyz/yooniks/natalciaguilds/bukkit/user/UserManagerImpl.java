package xyz.yooniks.natalciaguilds.bukkit.user;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import xyz.yooniks.natalciaguilds.api.user.User;
import xyz.yooniks.natalciaguilds.api.user.UserManager;

public class UserManagerImpl implements UserManager {

  private final Map<UUID, User> userMap = new HashMap<>();

  private final Cache<UUID, User> userCache = CacheBuilder.newBuilder()
      .expireAfterWrite(30, TimeUnit.MINUTES)
      .build();

  @Override
  public User createUser(UUID uuid) {
    User user = this.userMap.get(uuid);
    if (user == null) {
      final User cachedUser = this.userCache.getIfPresent(uuid);
      if (cachedUser != null) {
        this.userMap.put(uuid, user = cachedUser);
        return user;
      }
      this.userMap.put(uuid, user = new UserImpl(uuid));
    }
    return user;
  }

  @Override
  public void removeUser(UUID uuid) {
    final User user = this.userMap.get(uuid);
    if (user != null) {
      this.userCache.put(uuid, user);
    }
    this.userMap.remove(uuid);
  }

  @Override
  public List<User> getAll() {
    return null;
  }

}
