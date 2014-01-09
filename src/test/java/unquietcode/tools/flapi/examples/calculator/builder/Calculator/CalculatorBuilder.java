
/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

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
 * Generated on August 17, 2013 14:55:04 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "August 17, 2013 14:55:04 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface CalculatorBuilder<_ReturnType> {
    @MethodInfo(type = TransitionType.Ascending, chain = {
        CalculationBuilder.class
    })
    CalculationBuilder<_ReturnType> $(int startingValue);

    public interface $<_ReturnType>
        extends CalculatorBuilder<_ReturnType>
    {

    }
}
