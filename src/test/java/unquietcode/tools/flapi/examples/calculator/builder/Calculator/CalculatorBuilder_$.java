
package unquietcode.tools.flapi.examples.calculator.builder.Calculator;

import unquietcode.tools.flapi.examples.calculator.builder.Calculation.CalculationBuilder;
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
 * Generated on July 02, 2013 0:08:51 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "July 02, 2013 0:08:51 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface CalculatorBuilder_$<_ReturnType> {
    @MethodInfo(type = TransitionType.Ascending, chain = {
        CalculationBuilder.class
    })
    CalculationBuilder<_ReturnType> $(int startingValue);
}
