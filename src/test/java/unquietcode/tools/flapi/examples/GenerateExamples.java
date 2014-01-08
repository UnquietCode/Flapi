package unquietcode.tools.flapi.examples;

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
 * @version 2013-07-01
 */
public final class GenerateExamples {

	private static void generate(DescriptorMaker provider, String[] args) {
		Descriptor descriptor = provider.descriptor();
		descriptor.writeToFolder(args[0]);
	}

	/*
		Regenerates all of the examples.
	 */
	public static void main(String[] args) {
		generate(new CalculatorBuilderExample(), args);
		generate(new EmailBuilderExample(), args);
		generate(new HouseBuilderExample(), args);
		generate(new PipedProcessExample(), args);
		generate(new DisappearingPizzaExample(), args);
		generate(new XHTMLBuilderExample(), args);
	}
}
