package unquietcode.tools.flapi.annotations;

import unquietcode.tools.flapi.Constants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a method as being executable any number of times.
 *
 * @author Ben Fagin
 * @version 2014-08-03
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Any {
	int group() default Constants.DEFAULT_NULL_INT;
}
