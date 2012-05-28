package unquietcode.tools.flapi.examples.calculator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.Flapi;
import unquietcode.tools.flapi.examples.calculator.builder.CalculationBuilder;
import unquietcode.tools.flapi.examples.calculator.builder.CalculatorBuilder;
import unquietcode.tools.flapi.examples.calculator.builder.CalculatorGenerator;
import unquietcode.tools.flapi.examples.calculator.builder.ObjectWrapper;

import java.math.BigInteger;

/**
 * @author Ben Fagin
 * @version 05-11-2012
 */
public class CalculatorBuilderExample {

	@Rule
	public TemporaryFolder temp = new TemporaryFolder();

	@Test
	public void descriptorGenerator() {
		main(new String[]{temp.getRoot().getAbsolutePath()});
	}

	public static void main(String[] args) {
		Descriptor descriptor = Flapi.builder()
			.setDescriptorName("Calculator")
			.setPackage("unquietcode.tools.flapi.examples.calculator.builder")
			.setReturnType(ObjectWrapper.class)
			.enableCondensedClassNames(true)
			.setStartingMethodName("begin")

			.startBlock("Calculation", "$(int startingValue)").last()
				.addMethod("plus(int value)").any()
				.addMethod("minus(int value)").any()
				.addMethod("times(int value)").any()
				.addMethod("divide(int value)").any()
				.addMethod("power(int value)").any()
				.addMethod("mod(int value)").any()
				.addMethod("abs()").any()

				.addMethod("equals()").last()
			.endBlock()
		.build();

		descriptor.writeToFolder(args[0]);
	}

	@Test
	public void basicUsage() {
		ObjectWrapper _result = CalculatorGenerator.begin(new CalculatorHelperImpl())
			.$(0)
			.plus(1)
			.plus(1)
			.power(5)
			.divide(2)
		.equals();

		BigInteger result = (BigInteger) _result.get();
		System.out.println(result);
	}

	//-------------------------------------------------------------------------------------------//

	static class Calculator {
		static CalculationBuilder<ObjectWrapper<BigInteger>> begin(int startingValue) {
			CalculatorBuilder result = CalculatorGenerator.begin(new CalculatorHelperImpl());

			@SuppressWarnings("unchecked")
			CalculationBuilder<ObjectWrapper<BigInteger>> started
				= (CalculationBuilder<ObjectWrapper<BigInteger>>) result.$(startingValue);

			return started;
		}
	}

	@Test
	public void cleanedUpUsage() {
		ObjectWrapper<BigInteger> result = Calculator
		.begin(0)
			.plus(1)
			.plus(1)
			.power(5)
			.divide(2)
		.equals();

		System.out.println(result.get());
	}
}
