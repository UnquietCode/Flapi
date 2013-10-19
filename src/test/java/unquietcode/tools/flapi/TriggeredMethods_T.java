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

package unquietcode.tools.flapi;

import org.junit.Test;

/**
 * @author Ben Fagin
 * @version 04-10-2013
 */
public class TriggeredMethods_T extends AbstractCompiledTest {

	@Test
	public void testBasicTriggeredMethod() {
		Descriptor descriptor = Flapi.builder()
			.setDescriptorName("Something")
			.setPackage("something")

			.addMethod("dynamic()").atMost(1)
			.addMethod("dynamicWithGroup()").atMost(1, 1)
			.addMethod("triggered()").after(1).atLeast(1)
			.addMethod("terminal()").last()
		.build();

		descriptor.writeToFolder(getTemporaryFolder());
		testCompile("TriggeredTest2.avaj");
	}

	@Test
	public void testTriggeredAnyMethod() {
		Descriptor descriptor = Flapi.builder()
			.setDescriptorName("Something")
			.setPackage("something")

			.addMethod("required()").any()
			.addMethod("requiredWithGroup()").any(1)
			.addMethod("triggered()").after(1).atMost(1)
			.addMethod("terminal()").last()
		.build();

		descriptor.writeToFolder(getTemporaryFolder());

		addTestClassMethod("Test", loadResource("TriggeredTest1.avaj"));
		testCompile();
	}

	@Test(expected=DescriptorBuilderException.class)
	public void testTriggeringSameGroup() {
		Descriptor descriptor = Flapi.builder()
			.setDescriptorName("Something")
			.setPackage("something")

			.addMethod("triggered()").after(1).atMost(2, 1)
			.addMethod("terminal()").last()
		.build();

		descriptor.writeToStream(BlackHoleStream.$);
	}
}