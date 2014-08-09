package unquietcode.tools.flapi.annotations;

import unquietcode.tools.flapi.Constants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a method as being executable a maximum of X times.
 * Can also be associated with a group.
 *
 * @author Ben Fagin
 * @version 2014-08-03
 */
@FlapiAnnotation
@MethodQuantifier
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AtMost {
	int value();
	int group() default Constants.DEFAULT_NULL_INT;
}