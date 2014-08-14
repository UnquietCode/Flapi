/*********************************************************************
 Copyright 2014 the Flapi authors

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

package unquietcode.tools.flapi.outline;


import unquietcode.tools.flapi.MethodParser;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Ben Fagin
 * @version 03-07-2012
 */
public class DescriptorOutline extends BlockOutline implements Outline {
	private String packageName;
	private boolean enableCondensedNames = false;
	private final GeneratorOutline generator = new GeneratorOutline(this);

	public GeneratorOutline getGenerator() {
		return generator;
	}
	
	public String getPackageName() {
		return packageName;
	}

	public void enableCondensedNames(boolean value) {
		enableCondensedNames = value;
	}

	public boolean shouldEnableCondensedNames() {
		return enableCondensedNames;
	}

	public void setCreateMethod(String methodName) {
		generator.methodName = methodName;
	}

	public String getCreateMethod() {
		return generator.methodName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public void prepare() {
		generateNamesForAnonymousBlocks(this, new AtomicInteger(1));
	}

	private static void generateNamesForAnonymousBlocks(BlockOutline block, AtomicInteger counter) {

		// If the name is null, generate one.
		if (block.getName() == null || block.getName().trim().isEmpty()) {

			StringBuilder name = new StringBuilder()
				.append("Anon").append(counter.getAndIncrement())
			;

			// Purely anonymous blocks have no constructor!
			// Only anonymous block references have these.
			// The name is used only as a convenience.
			if (block.getConstructor() != null) {
				MethodParser parsed = new MethodParser(block.getConstructor().getMethodSignature());
				name.append("_").append(parsed.methodName);
			}

			block.setName(name.toString());
		}

		// recurse
		for (MethodOutline method : block.getAllMethods()) {
			for (BlockOutline chain : method.getBlockChain()) {
				if (chain == block) {
					continue;
				}

				generateNamesForAnonymousBlocks(chain, counter);
			}
		}
	}
}