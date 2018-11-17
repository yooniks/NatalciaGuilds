package xyz.yooniks.natalciaguilds.api.database;

public interface DatabaseDataConverter<A, B> {

  A toDatabaseColumn(B b);

  B fromDatabaseColumn(A a);

}
