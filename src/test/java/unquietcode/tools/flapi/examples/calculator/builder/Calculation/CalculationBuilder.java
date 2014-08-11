
package unquietcode.tools.flapi.examples.calculator.builder.Calculation;

import unquietcode.tools.flapi.examples.calculator.CalculatorBuilderExample.Result;
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
 * Generated on August 10, 2014 21:27:17 PDT using version 0.0-DEVELOPMENT
 */
@Generated(value = "unquietcode.tools.flapi", date = "2014-08-10T21:27:17-07:00", comments = "generated using Flapi, the fluent API generator for Java")
public interface CalculationBuilder {
    public interface Start<_ReturnType>
        extends CalculationBuilder_2abs_4f_2divide_4f_2minus_4f_2mod_4f_2plus_4f_2power_4f_2times_4f<_ReturnType>
    {
        @MethodInfo(type = TransitionType.Recursive)
        CalculationBuilder.Start<_ReturnType> abs();

        @MethodInfo(type = TransitionType.Recursive)
        CalculationBuilder.Start<_ReturnType> divide(int p0);

        @MethodInfo(type = TransitionType.Terminal)
        Result equals();

        @MethodInfo(type = TransitionType.Recursive)
        CalculationBuilder.Start<_ReturnType> minus(int p0);

        @MethodInfo(type = TransitionType.Recursive)
        CalculationBuilder.Start<_ReturnType> mod(int p0);

        @MethodInfo(type = TransitionType.Recursive)
        CalculationBuilder.Start<_ReturnType> plus(int p0);

        @MethodInfo(type = TransitionType.Recursive)
        CalculationBuilder.Start<_ReturnType> power(int p0);

        @MethodInfo(type = TransitionType.Recursive)
        CalculationBuilder.Start<_ReturnType> times(int p0);
    }
}
