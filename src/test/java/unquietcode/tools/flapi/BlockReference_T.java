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

package unquietcode.tools.flapi;

import org.junit.Test;

/**
 * @author Ben Fagin
 * @version 05-28-2012
 */
public class BlockReference_T extends AbstractCompiledTest {

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
					.addBlockReference("Three")
				.end()
			.any()
				.addMethod("exit()").last()
			.endBlock()

			.startBlock("Three", "three()").any()
				.addBlockReference("Two", "twoRef()").last() // this should go One->Two->exit
			.endBlock()

			.addMethod("exit()").last()
		.build();

		descriptor.writeToFolder(getTemporaryFolder());
		addTestClassMethod("Test", loadResource("BlockChainTest.avaj"));
		testCompile();
	}

	@Test
	public void testReferenceTopBlock() {
		Descriptor descriptor = Flapi.builder()
			.setDescriptorName("Something")
			.setPackage("some.thing")

			.startBlock("One", "one()").last()
				.addMethod("exit()").last()
				.addBlockReference("Something", "topLevel()").any()
			.endBlock()
		.build();

		descriptor.writeToFolder(getTemporaryFolder());
		addTestClassMethod("Test", loadResource("TopReference.avaj"));
		testCompile();
	}
}