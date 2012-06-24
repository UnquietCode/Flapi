
package unquietcode.tools.flapi.examples.calculator.builder;

import unquietcode.tools.flapi.support.v0_2.ObjectWrapper;

import javax.annotation.Generated;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on June 24, 2012 16:46:25 CDT using version 0.2
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "June 24, 2012 16:46:25 CDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface CalculationBuilder<_ReturnType >{


    CalculationBuilder<_ReturnType> abs();

    CalculationBuilder<_ReturnType> divide(int value);

    ObjectWrapper equals();

    CalculationBuilder<_ReturnType> minus(int value);

    CalculationBuilder<_ReturnType> mod(int value);

    CalculationBuilder<_ReturnType> plus(int value);

    CalculationBuilder<_ReturnType> power(int value);

    CalculationBuilder<_ReturnType> times(int value);

}
