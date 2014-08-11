/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.examples.calculator;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Ben Fagin
 * @version 05-12-2012
 */
public class CalculatorHelperImpl implements Calculator {
	private final CalculatorBuilderExample.Result value = new CalculatorBuilderExample.Result();

	CalculatorHelperImpl() { }

	@Override
	public void $(int startingValue, AtomicReference<Calculation> _helper1) {
		_helper1.set(new CalculationHelperImpl(value));
		value.set(BigInteger.valueOf(startingValue));
	}
}