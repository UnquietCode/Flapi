package unquietcode.tools.flapi.runtime;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a method for invocation tracking.
 *
 * @author Ben Fagin
 * @version 2013-06-09
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Tracked {
	int atLeast();
	String key();
}
