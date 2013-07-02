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

	/*
		Regenerates all of the examples.
	 */
	public static void main(String[] args) {
		CalculatorBuilderExample.main(args);
		EmailBuilderExample.main(args);
		HouseBuilderExample.main(args);
		PipedProcessExample.main(args);
		DisappearingPizzaExample.main(args);
		XHTMLBuilderExample.main(args);
	}
}
