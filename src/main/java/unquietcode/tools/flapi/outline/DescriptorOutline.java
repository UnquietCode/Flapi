/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.outline;


import unquietcode.tools.flapi.MethodParser;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Ben Fagin
 * @version 03-07-2012
 */
public class DescriptorOutline implements Outline {
	private String packageName;
	private boolean enableCondensedNames = false;
	public final BlockOutline selfBlock = new BlockOutline();
	private final GeneratorOutline generator = new GeneratorOutline(selfBlock);


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

	public void setDescriptorName(String name) {
		selfBlock.setName(name);
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
		generateNamesForAnonymousBlocks(selfBlock, new AtomicInteger(1));
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
