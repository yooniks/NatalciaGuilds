package xyz.yooniks.natalciaguilds.bukkit.config.system.color;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ColorValue {

  Class<? extends ColorChanger> colorBy() default StringColorChanger.class;

}
