
package unquietcode.tools.flapi.examples.calculator.builder.Calculation;

import unquietcode.tools.flapi.examples.calculator.CalculatorBuilderExample.Result;
import unquietcode.tools.flapi.examples.calculator.builder.Calculation.CalculationBuilder.Head;
import unquietcode.tools.flapi.runtime.MethodInfo;
import unquietcode.tools.flapi.runtime.TransitionType;

import javax.annotation.Generated;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on February 28, 2016 16:29:18 PST using version 0.0-DEVELOPMENT
 * @see unquietcode.tools.flapi.examples.calculator.Calculation
 */
@Generated(value = "unquietcode.tools.flapi", date = "2016-02-28T16:29:18-08:00", comments = "generated using Flapi, the fluent API generator for Java, version 0.0-DEVELOPMENT")
public interface CalculationBuilder_2abs_2divide_2minus_2mod_2plus_2power_2times<_ReturnType> {
    @MethodInfo(type = TransitionType.Recursive)
    Head<_ReturnType> abs();

    @MethodInfo(type = TransitionType.Recursive)
    Head<_ReturnType> divide(int arg0);

    @MethodInfo(type = TransitionType.Terminal)
    Result equals();

    @MethodInfo(type = TransitionType.Recursive)
    Head<_ReturnType> minus(int arg0);

    @MethodInfo(type = TransitionType.Recursive)
    Head<_ReturnType> mod(int arg0);

    @MethodInfo(type = TransitionType.Recursive)
    Head<_ReturnType> plus(int arg0);

    @MethodInfo(type = TransitionType.Recursive)
    Head<_ReturnType> power(int arg0);

    @MethodInfo(type = TransitionType.Recursive)
    Head<_ReturnType> times(int arg0);
}
