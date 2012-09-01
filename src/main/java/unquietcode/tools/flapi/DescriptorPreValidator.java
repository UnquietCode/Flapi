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
import unquietcode.tools.flapi.outline.BlockReference;
import unquietcode.tools.flapi.outline.DescriptorOutline;
import unquietcode.tools.flapi.outline.MethodOutline;

import javax.lang.model.SourceVersion;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Ben Fagin
 * @version 08-25-2012
 *
 * Validates a descriptor to ensure that minimum requirements are met.
 * The "pre" validator works on the {@link DescriptorOutline} model.
 */
public class DescriptorPreValidator {
	private final DescriptorOutline outline;

	public DescriptorPreValidator(DescriptorOutline outline) {
		this.outline = outline;
	}

	public void validate() {
		checkThatDescriptorMethodNameIsValid();
		checkForNameCollisions();
	}

	private void checkThatDescriptorMethodNameIsValid() {
		String name = outline.getCreateMethod();

		if (!SourceVersion.isName(name)) {
			throw new DescriptorBuilderException("Invalid method name for create method: '"+name+"'.");
		}
	}

	private void checkForNameCollisions() {
		_checkForNameCollisions(outline.selfBlock, new HashSet<String>());
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
