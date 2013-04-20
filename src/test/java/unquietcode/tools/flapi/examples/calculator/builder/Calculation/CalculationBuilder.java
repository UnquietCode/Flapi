
package unquietcode.tools.flapi.examples.calculator.builder.Calculation;

import unquietcode.tools.flapi.support.ObjectWrapper;

import javax.annotation.Generated;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on April 19, 2013 18:33:15 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "April 19, 2013 18:33:15 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface CalculationBuilder<_ReturnType> {
    CalculationBuilder<_ReturnType> abs();

    CalculationBuilder<_ReturnType> divide(int value);

    ObjectWrapper equals();

    CalculationBuilder<_ReturnType> minus(int value);

    CalculationBuilder<_ReturnType> mod(int value);

    CalculationBuilder<_ReturnType> plus(int value);

    CalculationBuilder<_ReturnType> power(int value);

    CalculationBuilder<_ReturnType> times(int value);
}
