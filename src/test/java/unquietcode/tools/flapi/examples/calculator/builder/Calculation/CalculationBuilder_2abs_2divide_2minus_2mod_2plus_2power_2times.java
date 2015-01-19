package unquietcode.tools.flapi.examples.calculator.builder.Calculation;

import unquietcode.tools.flapi.examples.calculator.CalculatorBuilderExample.Result;
import unquietcode.tools.flapi.examples.calculator.builder.Calculation.CalculationBuilder.Start;
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
 * Generated using version 0.0-DEVELOPMENT
 * @see unquietcode.tools.flapi.examples.calculator.Calculation
 */
@Generated(value = "unquietcode.tools.flapi", comments = "generated using Flapi, the fluent API generator for Java, version 0.0-DEVELOPMENT")
public interface CalculationBuilder_2abs_2divide_2minus_2mod_2plus_2power_2times<_ReturnType> {
    @MethodInfo(type = TransitionType.Recursive)
    Start<_ReturnType> abs();

    @MethodInfo(type = TransitionType.Recursive)
    Start<_ReturnType> divide(int p0);

    @MethodInfo(type = TransitionType.Terminal)
    Result equals();

    @MethodInfo(type = TransitionType.Recursive)
    Start<_ReturnType> minus(int p0);

    @MethodInfo(type = TransitionType.Recursive)
    Start<_ReturnType> mod(int p0);

    @MethodInfo(type = TransitionType.Recursive)
    Start<_ReturnType> plus(int p0);

    @MethodInfo(type = TransitionType.Recursive)
    Start<_ReturnType> power(int p0);

    @MethodInfo(type = TransitionType.Recursive)
    Start<_ReturnType> times(int p0);
}
