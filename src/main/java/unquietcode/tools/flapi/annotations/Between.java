package unquietcode.tools.flapi.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks method as needing to be executed a minimum of X times and
 * a maximum of Y times.
 *
 * @author Ben Fagin
 * @version 2014-08-03
 */
@FlapiAnnotation
@MethodQuantifier
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Between {
	int minInc();
	int maxInc();
}