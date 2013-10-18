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
 */
public class GroupedMethods_T extends AbstractCompiledTest {

	@Test
	public void testGroupedAnyMethod() {
		Descriptor descriptor = Flapi.builder()
			.setDescriptorName("Something")
			.setPackage("something")

			.addMethod("regular()").any()
			.addMethod("terminal()").last()

			.addMethod("required1()").any(1)
			.addMethod("optional1()").atMost(2, 1)

			.addMethod("required2A()").any(2)
			.addMethod("required2B()").any(2)
		.build();

		descriptor.writeToFolder(getTemporaryFolder());
		testCompile();

		testCompile("GroupedTest1.avaj");
		testCompile("GroupedTest2.avaj");
	}
}