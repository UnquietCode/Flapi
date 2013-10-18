/*******************************************************************************
 Copyright 2013 Benjamin Fagin

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.


     Read the included LICENSE.TXT for more information.
 ******************************************************************************/

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