/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.examples.calculator;

import unquietcode.tools.flapi.annotations.Any;
import unquietcode.tools.flapi.annotations.Last;

/**
* @author Ben Fagin
* @version 2014-08-05
*/
public interface Calculation {

	@Any void plus(int value);
	@Any void minus(int value);
	@Any void times(int value);
	@Any void divide(int value);
	@Any void power(int value);
	@Any void mod(int value);
	@Any void abs();

	@Last CalculatorBuilderExample.Result equals();
}