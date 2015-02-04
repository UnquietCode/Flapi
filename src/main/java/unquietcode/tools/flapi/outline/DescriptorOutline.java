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

import com.google.common.base.Function;
import unquietcode.tools.flapi.DescriptorBuilderException;
import unquietcode.tools.flapi.Utilities;
import unquietcode.tools.flapi.annotations.AnnotationIntrospector;
import unquietcode.tools.flapi.generator.naming.DefaultNameGenerator;
import unquietcode.tools.flapi.generator.naming.NameGenerator;
import unquietcode.tools.flapi.java.MethodSignature;

import java.util.Collection;
import java.util.Map;

/**
 * @author Ben Fagin
 * @version 03-07-2012
 */
public class DescriptorOutline extends BlockOutline implements Outline {
	private final GeneratorOutline generator = new GeneratorOutline(this);

	// one instance per outline, though it is ignored if the custom one is set
	private final NameGenerator defaultNameGenerator = new DefaultNameGenerator();

	private boolean isPrepared = false;
	private boolean enableCondensedNames = false;
	private boolean disableTimestamps = false;
	private String packageName;
	private NameGenerator customNameGenerator;

	public GeneratorOutline getGenerator() {
		return generator;
	}
	
	public String getPackageName() {
		return packageName;
	}

	public void enableCondensedNames(boolean value) {
		enableCondensedNames = value;
	}

	public void disableTimestamps(boolean value) {
		disableTimestamps = value;
	}

	public boolean shouldEnableCondensedNames() {
		return enableCondensedNames;
	}

	public boolean shouldDisableTimestamps() {
		return disableTimestamps;
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

	public NameGenerator getCustomNameGenerator() {
		return customNameGenerator;
	}

	public void setCustomNameGenerator(NameGenerator customNameGenerator) {
		this.customNameGenerator = customNameGenerator;
	}

	public void prepare() {
		if (isPrepared) { return; }

		generateNamesForAnonymousBlocks(this);
		reconcileMixins();

		isPrepared = true;
	}

	private void generateNamesForAnonymousBlocks(BlockOutline block) {

		// use the custom name generator if provided, otherwise the default
		final NameGenerator nameGenerator
			= customNameGenerator != null ? customNameGenerator : defaultNameGenerator;

		// If the name is null, generate one.
		if (block.getName() == null || block.getName().trim().isEmpty()) {
			final String name;

			// Purely anonymous blocks have no constructor!
			// Only anonymous block references have these.
			// The name is used only as a convenience.
			if (block.getConstructor() != null) {
				MethodSignature signature = block.getConstructor().getMethodSignature();
				name = nameGenerator.anonymousName(signature.methodName);
			} else {
				name = nameGenerator.anonymousName(null);
			}

			block.setName(name);
		}

		// recurse
		for (MethodOutline method : block.getAllMethods()) {
			for (BlockOutline chain : method.getBlockChain()) {
				if (chain == block) {
					continue;
				}

				generateNamesForAnonymousBlocks(chain);
			}
		}
	}

	private void reconcileMixins() {
		final Map<String, BlockOutline> allBlocks = BlockOutline.findAllBlocks(this);

		Utilities.safeRecurse(this, new Function<BlockOutline, Collection<BlockOutline>>() {
			public Collection<BlockOutline> apply(BlockOutline child) {
				reconcileMixins(allBlocks, child);
				return child.getBlocks();
			}
		});
	}

	private void reconcileMixins(Map<String, BlockOutline> allBlocks, BlockOutline block) {

		// for every named block mixin
		for (String mixin : block.getBlockMixins()) {
			BlockOutline mixinBlock = allBlocks.get(mixin);

			if (mixinBlock == null) {
				throw new DescriptorBuilderException("could not find mixin block with name '"+mixin+"'");
			}

			block.applyMixin(mixinBlock);
		}

		// for every typed block mixin
		for (Class<?> mixinType : block.getClassMixins()) {
			BlockOutline mixinBlock = AnnotationIntrospector.createBlock(mixinType);
			block.applyMixin(mixinBlock);
		}
	}
}