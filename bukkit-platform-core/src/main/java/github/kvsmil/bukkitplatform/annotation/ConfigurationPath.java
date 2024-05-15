package github.kvsmil.bukkitplatform.annotation;

import eu.okaeri.configs.serdes.OkaeriSerdesPack;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ConfigurationPath {
    String file();
    boolean removeOrphans() default false;

}
