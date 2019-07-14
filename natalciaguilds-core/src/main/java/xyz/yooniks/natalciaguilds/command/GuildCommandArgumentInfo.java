package xyz.yooniks.natalciaguilds.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface GuildCommandArgumentInfo {

  String usage() default "No description entered";

  String[] names();

  int minArgs() default 0;

  boolean playerOnly() default true;

}
