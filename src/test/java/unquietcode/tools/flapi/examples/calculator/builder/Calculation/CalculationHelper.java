
/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.examples.calculator.builder.Calculation;

import unquietcode.tools.flapi.examples.calculator.CalculatorBuilderExample.Result;

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
public interface CalculationHelper {
    void abs();

    void divide(int value);

    Result equals();

    void minus(int value);

    void mod(int value);

    void plus(int value);

    void power(int value);

    void times(int value);
}
