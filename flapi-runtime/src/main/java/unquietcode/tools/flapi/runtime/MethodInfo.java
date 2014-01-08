package unquietcode.tools.flapi.runtime;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Ben Fagin
 * @version 2013-06-30
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MethodInfo {
	TransitionType type();
	Class<?>[] chain() default {};
	Class<?> next() default MethodInfo.class;
}
