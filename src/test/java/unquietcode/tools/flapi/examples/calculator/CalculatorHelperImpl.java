package unquietcode.tools.flapi.examples.calculator;

import unquietcode.tools.flapi.examples.calculator.builder.Calculation.CalculationHelper;
import unquietcode.tools.flapi.examples.calculator.builder.Calculator.CalculatorHelper;
import unquietcode.tools.flapi.support.ObjectWrapper;

import java.math.BigInteger;

/**
 * @author Ben Fagin
 * @version 05-12-2012
 */
public class CalculatorHelperImpl implements CalculatorHelper {
	private final ObjectWrapper<BigInteger> value = new ObjectWrapper<BigInteger>();

	CalculatorHelperImpl() { }


	@Override
	public void $(int startingValue, ObjectWrapper<CalculationHelper> _helper1) {
		_helper1.set(new CalculationHelperImpl(value));
		value.set(BigInteger.valueOf(startingValue));
	}



}
