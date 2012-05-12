
package unquietcode.tools.flapi.examples.calculator.builder;



/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on May 12, 2012 24:56:40 CDT using version 0.1
 * 
 */
public interface CalculationBuilder<_ReturnType >{


    CalculationBuilder<_ReturnType> abs();

    CalculationBuilder<_ReturnType> divide(int value);

    _ReturnType equals();

    CalculationBuilder<_ReturnType> minus(int value);

    CalculationBuilder<_ReturnType> mod(int value);

    CalculationBuilder<_ReturnType> plus(int value);

    CalculationBuilder<_ReturnType> power(int value);

    CalculationBuilder<_ReturnType> times(int value);

}
