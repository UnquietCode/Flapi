
package unquietcode.tools.flapi.examples.calculator.builder.Calculation;

import unquietcode.tools.flapi.runtime.MethodInfo;
import unquietcode.tools.flapi.runtime.TransitionType;

import javax.annotation.Generated;
import java.util.concurrent.atomic.AtomicReference;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on July 02, 2013 0:08:51 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "July 02, 2013 0:08:51 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface CalculationBuilder<_ReturnType> {
    @MethodInfo(type = TransitionType.Recursive, chain = {

    })
    CalculationBuilder<_ReturnType> abs();

    @MethodInfo(type = TransitionType.Recursive, chain = {

    })
    CalculationBuilder<_ReturnType> divide(int value);

    @MethodInfo(type = TransitionType.Terminal, chain = {

    })
    AtomicReference equals();

    @MethodInfo(type = TransitionType.Recursive, chain = {

    })
    CalculationBuilder<_ReturnType> minus(int value);

    @MethodInfo(type = TransitionType.Recursive, chain = {

    })
    CalculationBuilder<_ReturnType> mod(int value);

    @MethodInfo(type = TransitionType.Recursive, chain = {

    })
    CalculationBuilder<_ReturnType> plus(int value);

    @MethodInfo(type = TransitionType.Recursive, chain = {

    })
    CalculationBuilder<_ReturnType> power(int value);

    @MethodInfo(type = TransitionType.Recursive, chain = {

    })
    CalculationBuilder<_ReturnType> times(int value);
}
