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

import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.DescriptorOutline;
import unquietcode.tools.flapi.outline.MethodOutline;

import javax.lang.model.SourceVersion;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Ben Fagin
 * @version 05-12-2012
 *
 * Validates a descriptor to ensure that minimum requirements are met.
 */
public class DescriptorValidator {
	private final DescriptorOutline descriptor;
	private final Object EXISTS = new Object();

	public DescriptorValidator(DescriptorOutline descriptor) {
		this.descriptor = descriptor;
	}

	public void validate() {
		checkThatDescriptorMethodNameIsValid();
		checkForNameCollisions();
		checkForBlocksWithNoEnd(descriptor.selfBlock);
	}

	private void checkThatDescriptorMethodNameIsValid() {
		String name = descriptor.getCreateMethod();

		if (!SourceVersion.isIdentifier(name)) {
			throw new DescriptorBuilderException("Invalid method name for create method: '"+name+"'.");
		}
	}

	private void checkForBlocksWithNoEnd(BlockOutline block) {
		if (block instanceof BlockReference) {
			return;
		}

		boolean valid = false;
		for (MethodOutline method : block.getAllMethods()) {
			if (method.isTerminal()) {
				valid = true;
				break;
			}
		}

		// FLAPI-73 - blocks with only dynamic methods should exit when empty
		if (block.getRequiredMethods().isEmpty() && !block.getDynamicMethods().isEmpty()) {
			valid = true;
		}

		// if there are no dynamic methods
		if (!valid) {
			throw new DescriptorBuilderException("Encountered a block with no terminal method: " + block.getName());
		}

		// recurse
		for (MethodOutline method : block.getAllMethods()) {
			for (BlockOutline chain : method.getBlockChain()) {
				checkForBlocksWithNoEnd(chain);
			}
		}
	}

	private void checkForNameCollisions() {
		_checkForNameCollisions(descriptor.selfBlock, new HashSet<String>());
	}

	private void _checkForNameCollisions(BlockOutline block, Set<String> names) {
		if (block instanceof BlockReference) {
			return;
		}

		// block name collisions
		String name = block.getName();
		if (names.contains(name)) {
			throw new DescriptorBuilderException("Duplicate block name encountered: "+ name);
		} else {
			names.add(name);
		}

		// check method name collisions
		for (MethodOutline method : block.getAllMethods()) {
			MethodParser curParsed = new MethodParser(method.getMethodSignature());

			for (MethodOutline otherMethod : block.getAllMethods()) {
				if (method == otherMethod) { continue; }
				MethodParser otherParsed = new MethodParser(otherMethod.getMethodSignature());

				if (curParsed.compilerEquivalent(otherParsed)) {
					throw new DescriptorBuilderException("Two methods with the same signature: " + method.getMethodSignature());
				}
			}
		}

		// recurse (We don't need to check child blocks since we get them from their constructors.)

		for (MethodOutline method : block.getAllMethods()) {
			for (BlockOutline chain : method.getBlockChain()) {
				if (chain == block) {
					continue;
				}

				_checkForNameCollisions(chain, names);
			}
		}
	}
}
