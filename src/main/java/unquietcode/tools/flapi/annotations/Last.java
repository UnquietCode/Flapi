package unquietcode.tools.flapi.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a method as being the last in a block.
 * The method may return a value. Every block must
 * have at least one @Last method in order to be
 * valid (and truly, to be at all escapable).
 *
 * @author Ben Fagin
 * @version 2014-08-03
 */
@MethodQuantifier
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Last {
	// nothing for now
}
