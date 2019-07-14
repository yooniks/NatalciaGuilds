package xyz.yooniks.natalciaguilds.bukkit.database.exception;

public class DatabaseIntializeException extends RuntimeException {

  public DatabaseIntializeException(String message) {
    super(message);
  }

  public DatabaseIntializeException(String message, Throwable cause) {
    super(message, cause);
  }

}
