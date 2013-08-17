package unquietcode.tools.flapi.examples.calculator;

import org.junit.Test;
import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.Flapi;
import unquietcode.tools.flapi.examples.calculator.builder.Calculation.CalculationBuilder;
import unquietcode.tools.flapi.examples.calculator.builder.Calculator.CalculatorBuilder;
import unquietcode.tools.flapi.examples.calculator.builder.Calculator.CalculatorGenerator;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Ben Fagin
 * @version 05-11-2012
 */
public class CalculatorBuilderExample {

	public static void main(String[] args) {
		Descriptor descriptor = Flapi.builder()
			.setDescriptorName("Calculator")
			.setPackage("unquietcode.tools.flapi.examples.calculator.builder")
			.setStartingMethodName("begin")

			.startBlock("Calculation", "$(int startingValue)").last()
				.addMethod("plus(int value)").any()
				.addMethod("minus(int value)").any()
				.addMethod("times(int value)").any()
				.addMethod("divide(int value)").any()
				.addMethod("power(int value)").any()
				.addMethod("mod(int value)").any()
				.addMethod("abs()").any()

				.addMethod("equals()").last(Result.class)
			.endBlock()
		.build();

		descriptor.writeToFolder(args[0]);
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
	}

	// ------- - - ------- -------- -  --     -- - -   ---- - -   -   -----------

	public static class Result extends AtomicReference<BigInteger> { }

	static class Calculator {
		static CalculationBuilder<AtomicReference<BigInteger>> begin(int startingValue) {
			CalculatorBuilder.$ result = CalculatorGenerator.begin(new CalculatorHelperImpl());

			@SuppressWarnings("unchecked")
			CalculationBuilder<AtomicReference<BigInteger>> started
				= (CalculationBuilder<AtomicReference<BigInteger>>) result.$(startingValue);

			return started;
		}
	}

	@Test
	public void cleanedUpUsage() {
		AtomicReference result = Calculator.begin(0)
			.plus(1)
			.plus(1)
			.power(5)
			.divide(2)
		.equals();

		System.out.println(result.get());
	}
}
