package unquietcode.tools.flapi.examples.calculator.builder.Calculator;

import unquietcode.tools.flapi.runtime.ChainInfo;
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
 * Generated on January 16, 2015 21:49:46 PST using version 0.0-DEVELOPMENT
 */
@Generated(value = "unquietcode.tools.flapi", date = "2015-01-16T21:49:46-08:00", comments = "generated using Flapi, the fluent API generator for Java, version 0.0-DEVELOPMENT")
public interface CalculatorBuilder<_ReturnType> {
    @MethodInfo(type = TransitionType.Ascending, chainInfo = {
        @ChainInfo(type = unquietcode.tools.flapi.examples.calculator.builder.Calculation.CalculationBuilder.Start.class, position = 1)
    })
    unquietcode.tools.flapi.examples.calculator.builder.Calculation.CalculationBuilder.Start<_ReturnType> $(int p0);

    public interface Start<_ReturnType>
        extends CalculatorBuilder<_ReturnType>
    {

    }
}
