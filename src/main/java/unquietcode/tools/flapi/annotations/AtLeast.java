package unquietcode.tools.flapi.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a method as needing to be executed a minimum of X times.
 *
 * @author Ben Fagin
 * @version 2014-08-03
 */
@MethodQuantifier
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AtLeast {
	int value();
}
