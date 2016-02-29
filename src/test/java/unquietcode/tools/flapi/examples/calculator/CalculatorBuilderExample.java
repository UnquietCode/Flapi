/*********************************************************************
 Copyright 2015 the Flapi authors

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ********************************************************************/

package unquietcode.tools.flapi.examples.calculator;

import org.junit.Test;
import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.DescriptorMaker;
import unquietcode.tools.flapi.Flapi;
import unquietcode.tools.flapi.examples.calculator.builder.Calculation.CalculationBuilder;
import unquietcode.tools.flapi.examples.calculator.builder.Calculator.CalculatorBuilder;
import unquietcode.tools.flapi.examples.calculator.builder.Calculator.CalculatorFactory;
import unquietcode.tools.flapi.examples.calculator.builder.Calculator.CalculatorGenerator;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;


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
		Result result = CalculatorGenerator.begin(new CalculatorHelperImpl())
			.$(0)
			.plus(1)
			.plus(1)
			.power(5)
			.divide(2)
		.equals();

		assertEquals(16, result.get().intValue());
	}

	// ------- - - ------- -------- -  --     -- - -   ---- - -   -   -----------

	public static class Result extends AtomicReference<BigInteger> { }

	static CalculationBuilder.Head<Void> begin(int startingValue) {
		CalculatorBuilder.Start begin = CalculatorGenerator.begin(new CalculatorHelperImpl());
		CalculationBuilder.Head<Void> start = begin.$(startingValue);

		return start;
	}

	@Test
	public void cleanedUpUsage() {
		Result result = begin(0)
			.plus(1)
			.plus(1)
			.power(5)
			.divide(2)
		.equals();

		assertEquals(16, result.get().intValue());
	}

	@Test
	public void factoryUsage() {
		CalculatorFactory factory = CalculatorGenerator.factory(new Supplier<Calculator>() {
			public @Override Calculator get() {
				return new CalculatorHelperImpl();
			}
		});

		Result result = factory.begin()
			.$(0)
			.plus(10)
			.minus(5)
		.equals();

		assertEquals(5, result.get().intValue());
	}
}
