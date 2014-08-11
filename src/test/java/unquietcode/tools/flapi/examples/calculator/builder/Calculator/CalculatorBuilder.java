
package unquietcode.tools.flapi.examples.calculator.builder.Calculator;

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
public interface CalculatorBuilder<_ReturnType> {
    public interface Start<_ReturnType>
        extends CalculatorBuilder<_ReturnType>
    {
        @MethodInfo(type = TransitionType.Ascending, chain = {
            unquietcode.tools.flapi.examples.calculator.builder.Calculation.CalculationBuilder.Start.class
        })
        unquietcode.tools.flapi.examples.calculator.builder.Calculation.CalculationBuilder.Start<_ReturnType> $(int p0);
    }
}
