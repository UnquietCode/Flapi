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
