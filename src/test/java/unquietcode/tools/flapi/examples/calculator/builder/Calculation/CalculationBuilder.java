
/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

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
 * Generated on August 17, 2013 14:55:04 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "August 17, 2013 14:55:04 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface CalculationBuilder<_ReturnType> {
    @MethodInfo(type = TransitionType.Recursive)
    CalculationBuilder<_ReturnType> abs();

    @MethodInfo(type = TransitionType.Recursive)
    CalculationBuilder<_ReturnType> divide(int value);

    @MethodInfo(type = TransitionType.Terminal)
    Result equals();

    @MethodInfo(type = TransitionType.Recursive)
    CalculationBuilder<_ReturnType> minus(int value);

    @MethodInfo(type = TransitionType.Recursive)
    CalculationBuilder<_ReturnType> mod(int value);

    @MethodInfo(type = TransitionType.Recursive)
    CalculationBuilder<_ReturnType> plus(int value);

    @MethodInfo(type = TransitionType.Recursive)
    CalculationBuilder<_ReturnType> power(int value);

    @MethodInfo(type = TransitionType.Recursive)
    CalculationBuilder<_ReturnType> times(int value);
}
