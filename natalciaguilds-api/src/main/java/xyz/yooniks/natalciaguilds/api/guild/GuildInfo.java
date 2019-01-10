package xyz.yooniks.natalciaguilds.api.guild;

public interface GuildInfo {

  <T> T getFlag(String flagName);

  void setFlag(String flagName, Object value);

  /*long getLastAttackTime();

  void setLastAttackTime(int time);

  long getValidityTime();

  void setValidityTime(int validityTime);

  long getCreationTime();

  void setCreationTime(long time);*/

  /*int getLives();

  void setLives(int lives);

  long getTime(TimeType timeType);

  void setTime(TimeType timeType, long time);

  enum TimeType {
    LAST_ATTACK_TIME,
    VALIDITY_TIME,
    CREATION_TIME
  }*/

}
