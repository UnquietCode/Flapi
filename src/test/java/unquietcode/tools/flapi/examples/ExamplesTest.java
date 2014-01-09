/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.examples;

import org.junit.Test;
import unquietcode.tools.flapi.AbstractCompiledTest;
import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.DescriptorMaker;
import unquietcode.tools.flapi.examples.calculator.CalculatorBuilderExample;
import unquietcode.tools.flapi.examples.email.EmailBuilderExample;
import unquietcode.tools.flapi.examples.house.HouseBuilderExample;
import unquietcode.tools.flapi.examples.pipes.PipedProcessExample;
import unquietcode.tools.flapi.examples.pizza.DisappearingPizzaExample;
import unquietcode.tools.flapi.examples.xhtml.XHTMLBuilderExample;

/**
 * @author Ben Fagin
 * @version 2013-10-18
 */
public class ExamplesTest extends AbstractCompiledTest {

	private void test(DescriptorMaker provider) {
		Descriptor descriptor = provider.descriptor();
		descriptor.writeToFolder(getTemporaryFolder());
		testCompile();
	}


	@Test
	public void CalculatorBuilder() {
		test(new CalculatorBuilderExample());
	}

	@Test
	public void EmailBuilder() {
		test(new EmailBuilderExample());
	}

	@Test
	public void HouseBuilder() {
		test(new HouseBuilderExample());
	}

	@Test
	public void PipedProcess() {
		test(new PipedProcessExample());
	}

	@Test
	public void PizzaBuilder() {
		test(new DisappearingPizzaExample());
	}

	@Test
	public void XHTMLBuilder() {
		test(new XHTMLBuilderExample());
	}
}