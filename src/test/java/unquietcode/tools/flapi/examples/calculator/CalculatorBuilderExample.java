/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.examples.calculator;

import org.junit.Test;
import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.DescriptorMaker;
import unquietcode.tools.flapi.Flapi;
import unquietcode.tools.flapi.examples.calculator.builder.Calculation.CalculationBuilder;
import unquietcode.tools.flapi.examples.calculator.builder.Calculator.CalculatorBuilder;
import unquietcode.tools.flapi.examples.calculator.builder.Calculator.CalculatorGenerator;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;

//import unquietcode.tools.flapi.examples.calculator.builder.Calculation.CalculationBuilder;
//import unquietcode.tools.flapi.examples.calculator.builder.Calculation.CalculationBuilder_2abs_4f_2divide_4f_2minus_4f_2mod_4f_2plus_4f_2power_4f_2times_4f;
//import unquietcode.tools.flapi.examples.calculator.builder.Calculator.CalculatorBuilder;
//import unquietcode.tools.flapi.examples.calculator.builder.Calculator.CalculatorGenerator;
//

/**
 * @author Ben Fagin
 * @version 05-11-2012
 */
public class CalculatorBuilderExample implements DescriptorMaker {

	@Override
	public Descriptor descriptor() {
		return Flapi.create(Calculator.class)
			.setPackage("unquietcode.tools.flapi.examples.calculator.builder")
			.setStartingMethodName("begin")
		.build();
	}

	@Test
	public void basicUsage() {
		Result _result = CalculatorGenerator.begin(new CalculatorHelperImpl())
			.$(0)
			.plus(1)
			.plus(1)
			.power(5)
			.divide(2)
		.equals();

		BigInteger result = _result.get();
		System.out.println(result);
		assertEquals(BigInteger.valueOf(16), result);
	}

	// ------- - - ------- -------- -  --     -- - -   ---- - -   -   -----------

	public static class Result extends AtomicReference<BigInteger> { }

	static CalculationBuilder.Start<Void> begin(int startingValue) {
		CalculatorBuilder.Start<Void> begin = CalculatorGenerator.begin(new CalculatorHelperImpl());
		CalculationBuilder.Start<Void> start = begin.$(startingValue);
		return start;
	}

	@Test
	public void cleanedUpUsage() {
		AtomicReference<BigInteger> result
		= begin(0)
			.plus(1)
			.plus(1)
			.power(5)
			.divide(2)
		.equals();

		System.out.println(result.get());
	}
}
