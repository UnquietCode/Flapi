/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies a method parameter that is to be used for building
 * a block chain around the method. The parameter must be of
 * type {@link java.util.concurrent.atomic.AtomicReference},
 * and the provided {@link #value()} class must match the
 * generic type of the reference object. A mismatch will not
 * be discovered until runtime, at which point you will receive
 * a ClassCastException.
 *
 * @author Ben Fagin
 * @version 2014-08-03
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface BlockChain {
	Class<?> value();
}