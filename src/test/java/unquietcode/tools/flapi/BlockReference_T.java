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

import org.junit.Test;
import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.MethodOutline;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * @author Ben Fagin
 * @version 05-28-2012
 */
public class BlockReference_T {

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

		// extract the ref and make sure it has methods

		List<BlockReference> refs = new ArrayList<BlockReference>();
		for (BlockOutline block : descriptor._descriptor.selfBlock.getBlocks()) {
			for (MethodOutline method : block.getAllMethods()) {
				for (BlockOutline chain : method.getBlockChain()) {
					if (chain instanceof BlockReference) {
						refs.add((BlockReference) chain);
					}
				}
			}
		}

		assertEquals("one ref", 1, refs.size());
		assertEquals("one method", 1, refs.get(0).getAllMethods().size());
	}
}
