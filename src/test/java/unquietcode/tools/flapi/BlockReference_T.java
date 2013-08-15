/*******************************************************************************
 Copyright 2012 Benjamin Fagin

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

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Ben Fagin
 * @version 05-28-2012
 */
public class BlockReference_T {

	// Even though this test is no longer relevant, it is still useful for visualizing the
	// result of the block referencing. Eventually it will benefit from the in-memory compilation
	// test harness (FLAPI-25).

	@Ignore("This test is irrelevant now.")
	@Test
	public void blockReferenceMethodsResolve() {
		Descriptor descriptor = Flapi.builder()
			.setDescriptorName("Something")
			.setPackage("some.thing")

			.startBlock("One", "one()").last()
				.addMethod("exit()").last()
			.endBlock()

			.startBlock("Two", "two()").any()
				.addBlockReference("One", "oneRef()").last()
			.endBlock()
		.build();

		descriptor.writeToStream(System.out);
	}

	@Ignore("unfinished")
	@Test
	public void testThatBlockReferencesKeepBlockChain() {
		Descriptor descriptor = Flapi.builder()
			.setDescriptorName("Something")
			.setPackage("some.thing")

			.startBlock("One", "one()").any()
				.addMethod("exit()").last()
			.endBlock()

			.startBlock("Two", "two()")
				.addBlockChain()
					.addBlockReference("One")
				.end()
			.any()
				.addMethod("exit()").last()
			.endBlock()

			.startBlock("Three", "three()").any()
				.addBlockReference("Two", "twoRef()").last() // this should go One->Two->exit
			.endBlock()

			.addMethod("exit()").last()
		.build();

		descriptor.writeToStream(System.out);
	}
}
